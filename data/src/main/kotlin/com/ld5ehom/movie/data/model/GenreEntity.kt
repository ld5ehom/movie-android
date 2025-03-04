package com.ld5ehom.movie.data.model

import com.ld5ehom.movie.data.DataMapper
import com.ld5ehom.movie.domain.model.Genre

data class GenreEntity(
    val id: Int,
    val name: String,
) : DataMapper<Genre> {
    override fun toDomain(): Genre =
        Genre(id, name)
}
