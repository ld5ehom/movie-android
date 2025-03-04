package com.ld5ehom.movie.ui.list

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import com.ld5ehom.movie.presentation.list.MovieListViewModel
import com.ld5ehom.movie.presentation.list.MovieListViewModel.Event
import com.ld5ehom.movie.ui.R
import com.ld5ehom.movie.ui.base.BaseFragment
import com.ld5ehom.movie.ui.databinding.FragmentMovieListBinding

@AndroidEntryPoint
class MovieListFragment :
    BaseFragment<FragmentMovieListBinding, MovieListViewModel, Event>(R.layout.fragment_movie_list) {

    override val viewModel: MovieListViewModel by viewModels()

    private val summaryMovieAdapter: SummaryMovieAdapter by lazy {
        SummaryMovieAdapter(SummaryMovieAdapter.Type.GRID)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        postponeEnterTransition()
    }

    private fun setupRecyclerView() {
        viewModel.summaryMovies.observe {
            summaryMovieAdapter.resetAll(it)
        }
        binding.recyclerView.adapter = summaryMovieAdapter
        binding.recyclerView.doOnPreDraw {
            startPostponedEnterTransition()
        }

    }

    override fun handleEvent(event: Event) {
        TODO("Not yet implemented")
    }

}
