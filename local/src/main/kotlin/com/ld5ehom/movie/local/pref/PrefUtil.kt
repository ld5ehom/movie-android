package com.ld5ehom.movie.local.pref

import android.app.Application
import com.ld5ehom.movie.local.model.GenreLocal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrefUtil @Inject constructor(application: Application) {

    private val sharedPreference = SharedPreference(application)

    var genres: List<GenreLocal>?
        get() = sharedPreference.get(PREF_GENRES)
        set(value) {
            if (value != null) {
                sharedPreference.put(PREF_GENRES, value)
            }
        }

    companion object {
        private const val PREF_GENRES = "PREF_GENRES"
    }
}
