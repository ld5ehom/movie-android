package com.ld5ehom.movie.domain.usecase

import com.ld5ehom.movie.domain.repository.MovieRepository
import javax.inject.Inject

class GetSummaryMovieListUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    operator fun invoke() =
        movieRepository.getSummaryMovies()
}
