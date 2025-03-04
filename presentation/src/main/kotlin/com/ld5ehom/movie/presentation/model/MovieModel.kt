package com.ld5ehom.movie.presentation.model

import com.ld5ehom.movie.domain.model.Movie
import java.util.Date

data class MovieModel(
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
    val genres: List<GenreModel>,
)

fun Movie.toPresentation(): MovieModel = MovieModel(
    id,
    name,
    description,
    posterImageUrl,
    backdropImageUrl,
    videoUrl,
    rating,
    rateCount,
    releasedAt,
    runtime,
    genres.map { it.toPresentation() },
)
