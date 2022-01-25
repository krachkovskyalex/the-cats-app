package com.krachkovsky.thecatsapp.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.krachkovsky.thecatsapp.R
import com.krachkovsky.thecatsapp.databinding.AllCatsItemBinding
import com.krachkovsky.thecatsapp.models.AnyCat

class CatViewHolder(private val binding: AllCatsItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(cat: AnyCat) {
        Glide.with(binding.root)
            .load(cat.url)
            .placeholder(R.drawable.loading_image)
            .error(R.drawable.error_image)
            .into(binding.ivMain)
    }
}