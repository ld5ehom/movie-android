package com.ld5ehom.movie.data.model

import com.ld5ehom.movie.data.DataMapper
import com.ld5ehom.movie.data.toDomain
import com.ld5ehom.movie.domain.model.Movie
import java.util.Date

data class MovieEntity(
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
    val genres: List<GenreEntity>,
) : DataMapper<Movie> {
    override fun toDomain(): Movie =
        Movie(
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
            genres.toDomain(),
        )
}
