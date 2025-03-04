package com.ld5ehom.movie.local.impl

import com.ld5ehom.movie.data.local.MovieLocalDataSource
import com.ld5ehom.movie.data.model.MovieEntity
import com.ld5ehom.movie.local.model.toLocal
import com.ld5ehom.movie.local.room.dao.MovieDao
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao,
) : MovieLocalDataSource {
    override suspend fun getMovie(movieId: Int): MovieEntity? =
        movieDao.getMovie(movieId)?.toData()

    override suspend fun saveMovie(movie: MovieEntity) {
        movieDao.insert(movie.toLocal())
    }
}
