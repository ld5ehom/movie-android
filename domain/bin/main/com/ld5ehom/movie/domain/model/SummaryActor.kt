package com.ld5ehom.movie.domain.model

data class SummaryActor(
    val id:Int,
    val name: String,
    val character: String,
    val popularity: Float,
    val profileImageUrl: String?,
    val profileDetailUrl: String,
)
