package com.ld5ehom.movie.data.local

import com.ld5ehom.movie.data.model.MovieEntity

interface MovieLocalDataSource {

    suspend fun getMovie(movieId: Int): MovieEntity?

    suspend fun saveMovie(movie: MovieEntity)
}
