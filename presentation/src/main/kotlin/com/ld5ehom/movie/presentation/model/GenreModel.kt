package com.ld5ehom.movie.presentation.model

import com.ld5ehom.movie.domain.model.Genre

data class GenreModel(
    val id: Int,
    val name: String,
)

fun Genre.toPresentation(): GenreModel = GenreModel(id, name)
