package com.ld5ehom.movie.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.ld5ehom.movie.local.model.MovieLocal
import com.ld5ehom.movie.local.room.RoomConstant

@Dao
interface MovieDao : BaseDao<MovieLocal> {

    @Query("SELECT * FROM ${RoomConstant.Table.MOVIE} WHERE id = :movieId")
    suspend fun getMovie(movieId: Int): MovieLocal?
}
