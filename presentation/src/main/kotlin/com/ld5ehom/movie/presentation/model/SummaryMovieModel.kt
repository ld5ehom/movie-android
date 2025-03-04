package com.ld5ehom.movie.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.ld5ehom.movie.domain.model.SummaryMovie
import java.util.Date

@Parcelize
data class SummaryMovieModel(
    val id: Int,
    val name: String,
    val description: String,
    val posterImageUrl: String,
    val rating: Float,
    val rateCount: Int,
    val releasedAt: Date,
) : Parcelable

fun SummaryMovie.toPresentation(): SummaryMovieModel =
    SummaryMovieModel(id, name, description, posterImageUrl, rating, rateCount, releasedAt)
