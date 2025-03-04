package com.ld5ehom.movie.domain.repository

import kotlinx.coroutines.flow.Flow
import com.ld5ehom.movie.data_resource.DataResource
import com.ld5ehom.movie.domain.model.SummaryActor
import com.ld5ehom.movie.domain.model.Movie
import com.ld5ehom.movie.domain.model.SummaryMovie

interface MovieRepository {

    fun getSummaryMovies(): Flow<DataResource<List<SummaryMovie>>>

    fun getMovie(movieId: Int): Flow<DataResource<Movie>>

    fun getActors(movieId: Int): Flow<DataResource<List<SummaryActor>>>

    fun getRecommendMovies(movieId: Int): Flow<DataResource<List<SummaryMovie>>>

    fun getSimilarMovies(movieId: Int): Flow<DataResource<List<SummaryMovie>>>
}
