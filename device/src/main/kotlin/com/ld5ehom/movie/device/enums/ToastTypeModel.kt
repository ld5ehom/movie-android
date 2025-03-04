package com.ld5ehom.movie.device.enums

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.ld5ehom.movie.design.R as DR


internal enum class ToastTypeModel(
    @DrawableRes val imageResId: Int,
    @ColorRes val backgroundResId: Int,
) {
    SUCCESS(
        DR.drawable.ic_check_24,
        DR.color.toast_success
    ),
    ERROR(
        DR.drawable.ic_error_24,
        DR.color.toast_error
    )
}
