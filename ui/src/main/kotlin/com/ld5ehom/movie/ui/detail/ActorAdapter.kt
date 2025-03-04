package com.ld5ehom.movie.ui.detail

import android.view.ViewGroup
import com.ld5ehom.movie.component.Device
import com.ld5ehom.movie.presentation.model.SummaryActorModel
import com.ld5ehom.movie.ui.R
import com.ld5ehom.movie.ui.base.adapter.BaseAdapter
import com.ld5ehom.movie.ui.base.adapter.BaseViewHolder
import com.ld5ehom.movie.ui.databinding.ItemSummaryActorBinding

class ActorAdapter(private val device: Device) :
    BaseAdapter<SummaryActorModel, ActorAdapter.GenreViewHolder>() {

    override fun getViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder =
        GenreViewHolder(parent)

    inner class GenreViewHolder(parent: ViewGroup) :
        BaseViewHolder<ItemSummaryActorBinding, SummaryActorModel>(
            parent,
            R.layout.item_summary_actor
        ) {
        init {

            itemView.setOnClickListener {
                val actor = binding.actor ?: return@setOnClickListener
                device.showWebUrl(actor.profileDetailUrl)
            }
        }

        override fun setData(data: SummaryActorModel) {
            binding.actor = data
        }
    }
}
