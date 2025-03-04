package com.ld5ehom.movie.local.model

import com.ld5ehom.movie.data.model.GenreEntity
import com.ld5ehom.movie.local.LocalMapper

data class GenreLocal(
    val id: Int,
    val name: String,
) : LocalMapper<GenreEntity> {
    override fun toData(): GenreEntity =
        GenreEntity(id, name)
}

fun GenreEntity.toLocal(): GenreLocal =
    GenreLocal(id, name)
