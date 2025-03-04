package com.ld5ehom.movie.ui.list

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.bumptech.glide.Glide
import com.ld5ehom.movie.presentation.model.SummaryMovieModel
import com.ld5ehom.movie.ui.NavigationMainDirections
import com.ld5ehom.movie.ui.R
import com.ld5ehom.movie.ui.base.adapter.BaseAdapter
import com.ld5ehom.movie.ui.base.adapter.BaseViewHolder
import com.ld5ehom.movie.ui.databinding.ItemSummaryMovieBinding
import com.ld5ehom.movie.ui.databinding.ItemSummaryMovieLinearBinding

class SummaryMovieAdapter(val type: Type) :
    BaseAdapter<SummaryMovieModel, SummaryMovieAdapter.BaseSummaryMovieViewHolder<out ViewDataBinding>>() {

    override fun getViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseSummaryMovieViewHolder<out ViewDataBinding> =
        when (type) {
            Type.GRID -> GridSummaryMovieViewHolder(parent)
            Type.LINEAR -> LinearSummaryMovieViewHolder(parent)
        }

    override fun resetAll(items: List<SummaryMovieModel>) {
        val diffCallback = SummaryMovieDiffCallback(this.items, items)
        resetAll(items, diffCallback)
    }


    inner class GridSummaryMovieViewHolder(parent: ViewGroup) :
        BaseSummaryMovieViewHolder<ItemSummaryMovieBinding>(parent, R.layout.item_summary_movie) {
        override val movieBinding: ItemSummaryMovieBinding
            get() = binding
    }

    inner class LinearSummaryMovieViewHolder(parent: ViewGroup) :
        BaseSummaryMovieViewHolder<ItemSummaryMovieLinearBinding>(
            parent,
            R.layout.item_summary_movie_linear
        ) {
        override val movieBinding: ItemSummaryMovieBinding
            get() = binding.itemSummaryMovie
    }

    @Suppress("LeakingThis")
    abstract class BaseSummaryMovieViewHolder<B : ViewDataBinding>(
        parent: ViewGroup,
        @LayoutRes layoutResId: Int
    ) :
        BaseViewHolder<B, SummaryMovieModel>(
            parent,
            layoutResId
        ) {

        abstract val movieBinding: ItemSummaryMovieBinding

        val imageView = movieBinding.ivPoster

        init {
            movieBinding.viewRoot.setOnClickListener {
                showMovieDetail()
            }
        }

        override fun setData(data: SummaryMovieModel) {
            movieBinding.movie = data
            movieBinding.executePendingBindings()
            Glide.with(movieBinding.ivPoster)
                .load(data.posterImageUrl)
                .centerCrop()
                .into(movieBinding.ivPoster)
        }

        private fun showMovieDetail() {
            val movie = movieBinding.movie ?: return
            val directions = NavigationMainDirections.actionMovieDetail(movie)
            val extras = FragmentNavigatorExtras(
         //       movieBinding.ivPoster to movieBinding.ivPoster.transitionName,
                movieBinding.viewRoot to movieBinding.viewRoot.transitionName,
            )
            movieBinding.root.findNavController().navigate(directions, extras)
        }
    }

    enum class Type {
        GRID, LINEAR
    }
}
