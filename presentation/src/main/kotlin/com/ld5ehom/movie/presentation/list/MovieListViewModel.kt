package com.ld5ehom.movie.presentation.list

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.ld5ehom.movie.data_resource.mapListDataResource
import com.ld5ehom.movie.domain.usecase.GetSummaryMovieListUseCase
import com.ld5ehom.movie.presentation.base.BaseViewModel
import com.ld5ehom.movie.presentation.base.ViewEvent
import com.ld5ehom.movie.presentation.list.MovieListViewModel.Event
import com.ld5ehom.movie.presentation.model.SummaryMovieModel
import com.ld5ehom.movie.presentation.model.toPresentation
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getSummaryMovieListUseCase: GetSummaryMovieListUseCase,
) : BaseViewModel<Event>() {

    private val _summaryMovies = MutableStateFlow<List<SummaryMovieModel>>(emptyList())
    val summaryMovies = _summaryMovies.asStateFlow()

    init {
        loadMovies()
    }

    private fun loadMovies() = launch {
        getSummaryMovieListUseCase()
            .mapListDataResource { it.toPresentation() }
            .collectDataResource({
                _summaryMovies.value = it
            })
    }

    sealed class Event : ViewEvent

}
