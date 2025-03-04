package com.ld5ehom.movie.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ld5ehom.movie.local.model.MovieLocal
import com.ld5ehom.movie.local.room.dao.MovieDao

@Database(
    entities = [MovieLocal::class],
    version = RoomConstant.ROOM_VERSION
)

@TypeConverters(
    DtoConverter::class
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}
