package com.ld5ehom.movie.remote.model

import com.google.gson.annotations.SerializedName
import com.ld5ehom.movie.data.model.GenreEntity
import com.ld5ehom.movie.remote.RemoteMapper

data class GenreResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
) : RemoteMapper<GenreEntity> {
    override fun toData(): GenreEntity =
        GenreEntity(id, name)
}
