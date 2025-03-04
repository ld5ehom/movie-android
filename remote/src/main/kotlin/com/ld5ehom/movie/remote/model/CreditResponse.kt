package com.ld5ehom.movie.remote.model

import com.google.gson.annotations.SerializedName
import com.ld5ehom.movie.data.model.SummaryActorEntity
import com.ld5ehom.movie.remote.Constant
import com.ld5ehom.movie.remote.RemoteMapper

data class CreditResponses(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("character")
    val character: String,
    @SerializedName("known_for_department")
    val department: String,
    @SerializedName("popularity")
    val popularity: Float,
    @SerializedName("profile_path")
    val profilePath: String?,
) : RemoteMapper<SummaryActorEntity> {

    private val profileImageUrl: String?
        get() = profilePath?.let {
            Constant.PROFILE_IMAGE_PREFIX_URL + it
        }

    private val profileDetailUrl: String
        get() = "$PROFILE_PAGE_PREFIX/$id-$name"

    override fun toData(): SummaryActorEntity =
        SummaryActorEntity(id, name, character, popularity, profileImageUrl, profileDetailUrl)

    companion object {
        private const val PROFILE_PAGE_PREFIX = "https://www.themoviedb.org/person"
    }
}

private const val ACTING = "Acting"

fun List<CreditResponses>.filterActor() = filter { it.department == ACTING }
