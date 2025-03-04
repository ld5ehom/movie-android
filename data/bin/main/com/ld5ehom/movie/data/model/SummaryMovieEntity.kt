package com.ld5ehom.movie.data.model

import com.ld5ehom.movie.data.DataMapper
import com.ld5ehom.movie.domain.model.SummaryMovie
import java.util.Date

data class SummaryMovieEntity(
    val id: Int,
    val name: String,
    val description: String,
    val posterImageUrl: String,
    val rating: Float,
    val rateCount: Int,
    val releasedAt: Date,
) : DataMapper<SummaryMovie> {
    override fun toDomain(): SummaryMovie =
        SummaryMovie(id, name, description, posterImageUrl, rating, rateCount, releasedAt)
}
