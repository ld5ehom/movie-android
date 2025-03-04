package com.ld5ehom.movie.domain.usecase

import com.ld5ehom.movie.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    operator fun invoke(movieId: Int) =
        movieRepository.getMovie(movieId)
}
