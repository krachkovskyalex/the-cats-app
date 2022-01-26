package com.krachkovsky.thecatsapp.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.krachkovsky.thecatsapp.R
import com.krachkovsky.thecatsapp.databinding.AllCatsItemBinding
import com.krachkovsky.thecatsapp.models.AnyCat

class CatViewHolder(
    private val binding: AllCatsItemBinding,
    private val onCatClick: (AnyCat) -> Unit,
    val fab: Int
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(cat: AnyCat) {
        with(binding) {
            Glide.with(root)
                .load(cat.url)
                .placeholder(R.drawable.loading_image)
                .error(R.drawable.error_image)
                .into(ivMain)
            fabAddFavorite.setImageResource(fab)
            fabAddFavorite.setOnClickListener {
                onCatClick(cat)
            }
        }
    }
}