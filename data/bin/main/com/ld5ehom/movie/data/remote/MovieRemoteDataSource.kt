package com.ld5ehom.movie.data.remote

import com.ld5ehom.movie.data.model.SummaryActorEntity
import com.ld5ehom.movie.data.model.MovieEntity
import com.ld5ehom.movie.data.model.SummaryMovieEntity

interface MovieRemoteDataSource {

    suspend fun getSummaryMovies(): List<SummaryMovieEntity>

    suspend fun getMovie(movieId: Int): MovieEntity

    suspend fun getActors(movieId: Int): List<SummaryActorEntity>

    suspend fun getRecommendMovies(movieId: Int): List<SummaryMovieEntity>

    suspend fun getSimilarMovies(movieId: Int): List<SummaryMovieEntity>
}
