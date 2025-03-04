package com.ld5ehom.movie.local.room

import androidx.room.TypeConverter
import com.ld5ehom.movie.common.extension.fromJson
import com.ld5ehom.movie.common.extension.toJson
import com.ld5ehom.movie.local.model.GenreLocal
import java.util.Date

class DtoConverter {

    @TypeConverter
    fun fromTimestamp(value: Long): Date = Date(value)

    @TypeConverter
    fun dateToTimestamp(date: Date): Long = date.time

    @TypeConverter
    fun fromGenres(genre: List<GenreLocal>) = genre.toJson()

    @TypeConverter
    fun toGenres(json: String) = json.fromJson<List<GenreLocal>>()

}
