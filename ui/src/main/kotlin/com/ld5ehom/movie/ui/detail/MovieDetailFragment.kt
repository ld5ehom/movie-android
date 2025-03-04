package com.ld5ehom.movie.ui.detail

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.ld5ehom.movie.component.Device
import com.ld5ehom.movie.design.extension.dp
import com.ld5ehom.movie.presentation.detail.MovieDetailViewModel
import com.ld5ehom.movie.presentation.model.MovieModel
import com.ld5ehom.movie.ui.R
import com.ld5ehom.movie.ui.base.BaseFragment
import com.ld5ehom.movie.ui.base.adapter.BaseAdapter
import com.ld5ehom.movie.ui.base.adapter.SpaceItemDecoration
import com.ld5ehom.movie.ui.databinding.FragmentMovieDetailBinding
import com.ld5ehom.movie.ui.extension.doOnApplyWindowInsets
import com.ld5ehom.movie.ui.extension.load
import com.ld5ehom.movie.ui.extension.updateConstraintSets
import com.ld5ehom.movie.ui.list.SummaryMovieAdapter
import com.ld5ehom.movie.ui.views.SimpleMotionLayoutListener
import javax.inject.Inject


@AndroidEntryPoint
class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailBinding, MovieDetailViewModel, MovieDetailViewModel.Event>(
        R.layout.fragment_movie_detail
    ) {
    override val viewModel: MovieDetailViewModel by viewModels()

    private val args: MovieDetailFragmentArgs by navArgs()

    @Inject
    lateinit var device: Device

    private val genreAdapter = GenreAdapter()
    private val actorAdapter: ActorAdapter by lazy { ActorAdapter(device) }
    private val recommendAdapter = SummaryMovieAdapter(SummaryMovieAdapter.Type.LINEAR)
    private val similarAdapter = SummaryMovieAdapter(SummaryMovieAdapter.Type.LINEAR)

    private var transitionState: Bundle? = null

    override fun handleEvent(event: MovieDetailViewModel.Event) {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBackPressedListener()
        val summaryMovie = args.summaryMovie
        viewModel.fetchMovie(summaryMovie.id)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            setPathMotion(MaterialArcMotion())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.transitionName = args.summaryMovie.name
        setupStatusBar()
        setupFab()
        setupRecyclerView()
        viewModel {
            movie observe {
                loadImage(it)
                genreAdapter.resetAll(it.genres)
            }
            actors observe { actorAdapter.resetAll(it) }
            recommendMovies.observe(recommendAdapter::resetAll)
            similarMovies.observe(similarAdapter::resetAll)
        }
        postponeEnterTransition()
    }

    private fun loadImage(movie: MovieModel) = lifecycleScope.launch {
        binding.ivPoster.load(movie.posterImageUrl)
        transitionState?.let { binding.motionLayoutDetail.transitionState = it }
        startPostponedEnterTransition()
    }

    private fun setupStatusBar() {
        binding.motionLayoutDetail.doOnApplyWindowInsets { v, insets, _ ->
            (v as MotionLayout).updateConstraintSets {
                constrainHeight(R.id.anchor_status_bar, insets.systemWindowInsetTop)
            }
        }

        // Make the MotionLayout draw behind the status bar
        binding.motionLayoutDetail.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }

    private fun setupFab() {
        binding.motionLayoutDetail.addTransitionListener(object : SimpleMotionLayoutListener() {
            override fun onTransitionCompleted(parent: MotionLayout, currentId: Int) {
                when (currentId) {
                    R.id.show_details_open -> {
                        if (!binding.fabPlay.isOrWillBeShown) {
                            binding.fabPlay.show()
                        }
                    }
                    R.id.show_details_closed -> {
                        if (!binding.fabPlay.isOrWillBeHidden) {
                            binding.fabPlay.hide()
                        }
                    }
                }
            }

            override fun onTransitionChange(
                parent: MotionLayout,
                startId: Int,
                endId: Int,
                progress: Float,
                positive: Boolean
            ) {
                if (startId == R.id.show_details_open && endId == R.id.show_details_closed) {
                    if (progress >= 0.53 && positive &&
                        !binding.fabPlay.isOrWillBeHidden
                    ) {
                        binding.fabPlay.hide()
                    } else if (progress <= 0.47 && !positive && !binding.fabPlay.isOrWillBeShown) {
                        binding.fabPlay.show()
                    }
                }
            }
        })
    }

    private fun setupRecyclerView() {
        with(binding.layoutMovieDetailContent) {
            rvGenre.setup(genreAdapter)
            rvActor.setup(actorAdapter)
            rvRecommend.setup(recommendAdapter)
            rvSimilar.setup(similarAdapter)
        }
    }

    private fun RecyclerView.setup(adapter: BaseAdapter<*, *>) {
        addItemDecoration(SpaceItemDecoration(8.dp))
        this.adapter = adapter
    }

    private fun setupBackPressedListener() {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    fun finish() {
        if (binding.motionLayoutDetail.currentState == binding.motionLayoutDetail.endState) {
            binding.motionLayoutDetail.transitionToStart()
            return
        }
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        transitionState = binding.motionLayoutDetail.transitionState
        super.onDestroyView()
    }

}
