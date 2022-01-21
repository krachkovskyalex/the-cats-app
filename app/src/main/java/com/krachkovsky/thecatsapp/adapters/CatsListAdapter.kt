package com.krachkovsky.thecatsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.krachkovsky.thecatsapp.databinding.AllCatsItemBinding
import com.krachkovsky.thecatsapp.models.AnyCat

class CatsListAdapter : ListAdapter<AnyCat, CatViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder(
            binding = AllCatsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AnyCat>() {
            override fun areItemsTheSame(oldItem: AnyCat, newItem: AnyCat): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: AnyCat, newItem: AnyCat): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}