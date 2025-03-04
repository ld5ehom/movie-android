package com.ld5ehom.movie.remote.api

import android.content.Context
import com.ld5ehom.movie.common.extension.fromJson
import com.ld5ehom.movie.remote.model.CreditWrapperResponses
import com.ld5ehom.movie.remote.model.MovieResponse
import com.ld5ehom.movie.remote.model.SummaryMovieWrapperResponse
import com.ld5ehom.movie.remote.model.VideoWrapperResponse

class FakeApiService(private val context: Context) : ApiService {
    override suspend fun getSummaryMovies(
        language: String,
        country: String
    ): SummaryMovieWrapperResponse =
        getResponse("movies.json")

    override suspend fun getMovie(movieId: Int, language: String): MovieResponse =
        getResponse("movie.json")

    override suspend fun getVideos(movieId: Int, language: String): VideoWrapperResponse {
        throw NotImplementedError("getVideos is not implemented yet")
    }

    override suspend fun getActors(movieId: Int, language: String): CreditWrapperResponses {
        throw NotImplementedError("getActors is not implemented yet")
    }

    override suspend fun getRecommendMovies(
        movieId: Int,
        language: String
    ): SummaryMovieWrapperResponse {
        throw NotImplementedError("getRecommendMovies is not implemented yet")
    }

    override suspend fun getSimilarMovies(
        movieId: Int,
        language: String
    ): SummaryMovieWrapperResponse {
        throw NotImplementedError("getSimilarMovies is not implemented yet")
    }

    private inline fun <reified T> getResponse(fileName: String): T {
        context.assets.open(fileName).use { inputStream ->
            val size: Int = inputStream.available()
            val buffer = ByteArray(size).apply {
                inputStream.read(this)
            }
            return String(buffer).fromJson<T>()!!
        }
    }
}
