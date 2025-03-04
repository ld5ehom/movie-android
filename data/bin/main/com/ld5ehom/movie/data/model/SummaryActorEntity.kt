package com.ld5ehom.movie.data.model

import com.ld5ehom.movie.data.DataMapper
import com.ld5ehom.movie.domain.model.SummaryActor

data class SummaryActorEntity(
    val id: Int,
    val name: String,
    val character: String,
    val popularity: Float,
    val profileImageUrl: String?,
    val profileDetailUrl: String,
) : DataMapper<SummaryActor> {
    override fun toDomain(): SummaryActor =
        SummaryActor(id, name, character, popularity, profileImageUrl, profileDetailUrl)
}
