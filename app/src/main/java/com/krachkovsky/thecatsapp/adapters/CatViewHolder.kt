package com.krachkovsky.thecatsapp.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.krachkovsky.thecatsapp.databinding.CatItemBinding
import com.krachkovsky.thecatsapp.models.AnyCat

class CatViewHolder(private val binding: CatItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(cat: AnyCat) {
        Glide.with(binding.root).load(cat.url).into(binding.ivMain)
    }
}