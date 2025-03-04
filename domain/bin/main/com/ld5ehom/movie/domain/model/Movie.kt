package com.ld5ehom.movie.domain.model

import java.util.Date

data class Movie(
    val id: Int,
    val name: String,
    val description: String,
    val posterImageUrl: String,
    val backdropImageUrl: String,
    val videoUrl: String,
    val rating: Float,
    val rateCount: Int,
    val releasedAt: Date,
    val runtime: Int,
    val genres: List<Genre>,
)
