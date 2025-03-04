package com.ld5ehom.movie.domain.model

data class FilterInfo(
    val genres: List<Genre>
) {
    val rating: IntRange = IntRange(0, 10)
    val runtime: IntRange = IntRange(0, 400)
}
