package com.ld5ehom.movie.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ld5ehom.movie.data.model.MovieEntity
import com.ld5ehom.movie.local.LocalMapper
import com.ld5ehom.movie.local.room.RoomConstant
import com.ld5ehom.movie.local.toData
import java.util.Date

@Entity(tableName = RoomConstant.Table.MOVIE)
data class MovieLocal(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val posterImageUrl: String,
    val backdropImageUrl: String,
    val videoUrl: String,
    val rating: Float,
    val rateCount: Int,
    val releasedAt: Date,
    val runtime: Int,
    val genres: List<GenreLocal>,
) : LocalMapper<MovieEntity> {
    override fun toData(): MovieEntity =
        MovieEntity(
            id,
            name,
            description,
            posterImageUrl,
            backdropImageUrl,
            videoUrl,
            rating,
            rateCount,
            releasedAt,
            runtime,
            genres.toData(),
        )
}


fun MovieEntity.toLocal(): MovieLocal =
    MovieLocal(
        id,
        name,
        description,
        posterImageUrl,
        backdropImageUrl,
        videoUrl,
        rating,
        rateCount,
        releasedAt,
        runtime,
        genres.map { it.toLocal() },
    )
