package com.krachkovsky.thecatsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.krachkovsky.thecatsapp.databinding.AllCatsItemBinding
import com.krachkovsky.thecatsapp.models.AnyCat

class CatsPagingDataAdapter(
    private val fab: Int,
    private val onCatClick: (AnyCat) -> Unit
) :
    PagingDataAdapter<AnyCat, CatViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        return CatViewHolder(
            binding = AllCatsItemBinding.inflate(layoutInflater, parent, false),
            onCatClick = onCatClick,
            fab = fab
        )
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AnyCat>() {

            override fun areItemsTheSame(
                oldItem: AnyCat,
                newItem: AnyCat
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: AnyCat,
                newItem: AnyCat
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}