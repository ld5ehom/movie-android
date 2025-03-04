package com.ld5ehom.movie.presentation.model

import com.ld5ehom.movie.domain.model.SummaryActor

data class SummaryActorModel(
    val id: Int,
    val name: String,
    val character: String,
    val popularity: Float,
    val profileImageUrl: String?,
    val profileDetailUrl: String,
)

fun SummaryActor.toPresentation(): SummaryActorModel =
    SummaryActorModel(id, name, character, popularity, profileImageUrl, profileDetailUrl)
