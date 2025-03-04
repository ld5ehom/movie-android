package com.ld5ehom.movie.presentation.detail

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.ld5ehom.movie.component.Device
import com.ld5ehom.movie.data_resource.mapDataResource
import com.ld5ehom.movie.data_resource.mapListDataResource
import com.ld5ehom.movie.domain.usecase.GetActorListUseCase
import com.ld5ehom.movie.domain.usecase.GetMovieUseCase
import com.ld5ehom.movie.domain.usecase.GetRecommendMovieListUseCase
import com.ld5ehom.movie.domain.usecase.GetSimilarMovieListUseCase
import com.ld5ehom.movie.presentation.base.BaseViewModel
import com.ld5ehom.movie.presentation.base.ViewEvent
import com.ld5ehom.movie.presentation.detail.MovieDetailViewModel.Event
import com.ld5ehom.movie.presentation.model.SummaryActorModel
import com.ld5ehom.movie.presentation.model.MovieModel
import com.ld5ehom.movie.presentation.model.SummaryMovieModel
import com.ld5ehom.movie.presentation.model.toPresentation
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val device: Device,
    private val getMovieUseCase: GetMovieUseCase,
    private val getActorListUseCase: GetActorListUseCase,
    private val getRecommendMovieListUseCase: GetRecommendMovieListUseCase,
    private val getSimilarMovieListUseCase: GetSimilarMovieListUseCase,
) : BaseViewModel<Event>() {

    private val _movie = MutableStateFlow<MovieModel?>(null)
    val movie = _movie.asStateFlow()

    private val _actors = MutableStateFlow<List<SummaryActorModel>>(emptyList())
    val actors = _actors.asStateFlow()

    private val _recommendMovies = MutableStateFlow<List<SummaryMovieModel>>(emptyList())
    val recommendMovies = _recommendMovies.asStateFlow()

    private val _similarMovies = MutableStateFlow<List<SummaryMovieModel>>(emptyList())
    val similarMovies = _similarMovies.asStateFlow()

    fun fetchMovie(movieId: Int) {
        launch {
            _movie.value = getMovieUseCase(movieId)
                .mapDataResource { it.toPresentation() }
                .await() ?: return@launch
        }
        launch {
            _actors.value = getActorListUseCase(movieId)
                .mapListDataResource { it.toPresentation() }
                .await() ?: return@launch
        }
        launch {
            _recommendMovies.value = getRecommendMovieListUseCase(movieId)
                .mapListDataResource { it.toPresentation() }
                .await() ?: return@launch
        }
        launch {
            _similarMovies.value = getSimilarMovieListUseCase(movieId)
                .mapListDataResource { it.toPresentation() }
                .await() ?: return@launch
        }
    }

    fun showVideo() {
        val videoUrl = movie.value?.videoUrl ?: return
        if (videoUrl.isNotBlank()) {
            device.showWebUrl(videoUrl)
        }
    }

    sealed class Event : ViewEvent {

    }
}
