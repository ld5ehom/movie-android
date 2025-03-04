package com.ld5ehom.movie.ui.detail

import android.view.ViewGroup
import com.ld5ehom.movie.presentation.model.GenreModel
import com.ld5ehom.movie.ui.R
import com.ld5ehom.movie.ui.base.adapter.BaseAdapter
import com.ld5ehom.movie.ui.base.adapter.BaseViewHolder
import com.ld5ehom.movie.ui.databinding.ItemGenreBinding

class GenreAdapter : BaseAdapter<GenreModel, GenreAdapter.GenreViewHolder>() {

    override fun getViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder =
        GenreViewHolder(parent)

    class GenreViewHolder(parent: ViewGroup) :
        BaseViewHolder<ItemGenreBinding, GenreModel>(parent, R.layout.item_genre) {
        override fun setData(data: GenreModel) {
            binding.genre = data
        }
    }
}
