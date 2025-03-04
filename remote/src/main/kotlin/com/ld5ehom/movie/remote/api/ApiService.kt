package com.ld5ehom.movie.remote.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.ld5ehom.movie.remote.model.CreditWrapperResponses
import com.ld5ehom.movie.remote.model.MovieResponse
import com.ld5ehom.movie.remote.model.SummaryMovieWrapperResponse
import com.ld5ehom.movie.remote.model.VideoWrapperResponse

interface ApiService {
    @GET("discover/movie?&sort_by=popularity.desc")
    suspend fun getSummaryMovies(
        @Query("language") language: String,
        @Query("region") country: String,
    ): SummaryMovieWrapperResponse

    @GET("movie/{movieId}")
    suspend fun getMovie(
        @Path("movieId") movieId: Int,
        @Query("language") language: String,
    ): MovieResponse

    @GET("movie/{movieId}/videos")
    suspend fun getVideos(
        @Path("movieId") movieId: Int,
        @Query("language") language: String,
    ): VideoWrapperResponse

    @GET("movie/{movieId}/credits")
    suspend fun getActors(
        @Path("movieId") movieId: Int,
        @Query("language") language: String,
    ): CreditWrapperResponses

    @GET("movie/{movieId}/recommendations")
    suspend fun getRecommendMovies(
        @Path("movieId") movieId: Int,
        @Query("language") language: String,
    ): SummaryMovieWrapperResponse

    @GET("movie/{movieId}/similar")
    suspend fun getSimilarMovies(
        @Path("movieId") movieId: Int,
        @Query("language") language: String,
    ): SummaryMovieWrapperResponse

}
