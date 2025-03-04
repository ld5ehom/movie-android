package com.ld5ehom.movie.device.impl

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Build
import android.preference.PreferenceManager
import android.provider.Settings
import android.telephony.TelephonyManager
import android.text.TextUtils
import androidx.ads.identifier.AdvertisingIdClient
import androidx.core.net.toUri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.ld5ehom.movie.component.Device
import com.ld5ehom.movie.component.ErrorHandler
import java.util.Locale
import javax.inject.Inject

internal class DeviceImpl @Inject constructor(
    private val application: Application,
    private val errorHandler: ErrorHandler,
) : Device {

    private val sharedPreference = PreferenceManager.getDefaultSharedPreferences(application)

    override fun sendSms(phoneNumber: String, text: String?) {
        startActivity {
            Intent(Intent.ACTION_SEND, "$SCHEME_SMS:$phoneNumber".toUri())
                .apply {
                    putExtra(SMS_BODY, text)
                }
        }
    }

    override fun call(phoneNumber: String) {
        startActivity {
            Intent(Intent.ACTION_CALL, "$SCHEME_TEL:$phoneNumber".toUri())
        }
    }

    override fun dial(phoneNumber: String) {
        startActivity {
            Intent(Intent.ACTION_DIAL, "$SCHEME_TEL:$phoneNumber".toUri())
        }
    }

    override fun showWebUrl(url: String) {
        startActivity {
            Intent(Intent.ACTION_VIEW, url.toUri())
        }
    }

    private fun startActivity(intent: () -> Intent) {
        application.startActivity(
            intent().apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        )
    }

    override val modelName: String = "${Build.BRAND} ${Build.MODEL}"

    override val osVersion: String = Build.VERSION.RELEASE

    override val phoneNumber: String
        @SuppressLint("MissingPermission", "HardwareIds")
        get() = runCatching {
            val telephonyManager =
                application.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            telephonyManager.line1Number
                ?.takeIf { isKoreaPhoneNumber(it) }
                ?.replace(KOREA_NUMBER_CODE, "0") ?: ""
        }.getOrDefault("")

    override suspend fun getUserUniqueId(): String =
        when (val advertisingId = getAdvertisingId()) {
            null -> {
                errorHandler.handleUnExpectedCaseException { "광고 ID를 가져올 수 없음" }
                deviceId
            }
            ADVERTISING_ID_EMPTY -> deviceId
            else -> advertisingId
        }

    override suspend fun getAdvertisingId(): String? = withContext(Dispatchers.IO) {
        runCatching {
            AdvertisingIdClient.getAdvertisingIdInfo(application).get().id
        }
            .recoverCatching { AdvertisingIdClient.getAdvertisingIdInfo(application).get().id }
            .getOrNull()
    }

    override val savedUserUniqueId: String?
        get() = sharedPreference.getString(PREF_USER_UNIQUE_ID, null)

    override val deviceId: String
        @SuppressLint("HardwareIds")
        get() = runCatching {
            val contentResolver = application.contentResolver
            Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        }
            .onFailure { errorHandler.logException(it) }
            .getOrDefault("알 수 없음")

    private fun isKoreaPhoneNumber(phoneNumber: String): Boolean =
        when {
            TextUtils.isEmpty(phoneNumber) -> false

            // 국가번호 없거나, +82 한국번호로 시작해야 한국 전화번호로 판단
            // 통신사에 따라서 국가번호가 붙거나 없거나여서 2가지경우를 모두 처리
            else -> !phoneNumber.startsWith("+") || phoneNumber.startsWith(KOREA_NUMBER_CODE)
        }

    override val language: String
        get() = Locale.getDefault().language

    override val country: String
        get() = Locale.getDefault().country

    companion object {
        private const val SMS_BODY = "sms_body"
        private const val SCHEME_TEL = "tel"
        private const val SCHEME_SMS = "smsto"

        private const val KOREA_NUMBER_CODE = "+82"
        private const val PREF_USER_UNIQUE_ID = "PREF_USER_UNIQUE_ID"
        private const val ADVERTISING_ID_EMPTY = "00000000-0000-0000-0000-000000000000"

    }
}
