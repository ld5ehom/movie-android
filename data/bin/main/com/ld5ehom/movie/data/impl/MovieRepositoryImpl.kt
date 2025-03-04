package com.ld5ehom.movie.data.impl

import kotlinx.coroutines.flow.Flow
import com.ld5ehom.movie.data.bound.flowDataResource
import com.ld5ehom.movie.data.local.MovieLocalDataSource
import com.ld5ehom.movie.data.remote.MovieRemoteDataSource
import com.ld5ehom.movie.data_resource.DataResource
import com.ld5ehom.movie.domain.model.SummaryActor
import com.ld5ehom.movie.domain.model.Movie
import com.ld5ehom.movie.domain.model.SummaryMovie
import com.ld5ehom.movie.domain.repository.MovieRepository
import javax.inject.Inject

internal class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
) : MovieRepository {
    override fun getSummaryMovies(): Flow<DataResource<List<SummaryMovie>>> =
        flowDataResource { movieRemoteDataSource.getSummaryMovies() }

    override fun getMovie(movieId: Int): Flow<DataResource<Movie>> =
        flowDataResource(
            { movieRemoteDataSource.getMovie(movieId) },
            { movieLocalDataSource.getMovie(movieId) },
            { movieLocalDataSource.saveMovie(it) }
        )

    override fun getActors(movieId: Int): Flow<DataResource<List<SummaryActor>>> =
        flowDataResource { movieRemoteDataSource.getActors(movieId) }

    override fun getRecommendMovies(movieId: Int): Flow<DataResource<List<SummaryMovie>>> =
        flowDataResource { movieRemoteDataSource.getRecommendMovies(movieId) }

    override fun getSimilarMovies(movieId: Int): Flow<DataResource<List<SummaryMovie>>> =
        flowDataResource { movieRemoteDataSource.getSimilarMovies(movieId) }
}
