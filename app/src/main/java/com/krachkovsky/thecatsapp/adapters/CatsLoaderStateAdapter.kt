package com.krachkovsky.thecatsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.krachkovsky.thecatsapp.R
import com.krachkovsky.thecatsapp.databinding.ErrorItemBinding
import com.krachkovsky.thecatsapp.databinding.LoadingItemBinding

class CatsLoaderStateAdapter : LoadStateAdapter<CatsLoaderStateAdapter.ItemViewHolder>() {

    override fun getStateViewType(loadState: LoadState) = when (loadState) {
        is LoadState.NotLoading -> error("Not supported")
        LoadState.Loading -> PROGRESS
        is LoadState.Error -> ERROR
    }

    override fun onBindViewHolder(holder: ItemViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ItemViewHolder {
        return when (loadState) {
            LoadState.Loading -> ProgressViewHolder(
                binding = LoadingItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            is LoadState.Error -> ErrorViewHolder(
                binding = ErrorItemBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
            is LoadState.NotLoading -> error("Not supported")
        }
    }

    companion object {
        private const val ERROR = 1
        private const val PROGRESS = 0

    }

    abstract class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(loadState: LoadState)
    }

    class ProgressViewHolder(binding: LoadingItemBinding) : ItemViewHolder(binding.root) {

        private val _binding = binding

        override fun bind(loadState: LoadState) {
            Glide.with(_binding.root)
                .load(R.drawable.progress_item)
                .centerCrop()
                .into(_binding.ivLoading)
        }

        companion object {

            operator fun invoke(
                layoutInflater: LayoutInflater,
                parent: ViewGroup? = null,
                attachToRoot: Boolean = false
            ): ProgressViewHolder {

                return ProgressViewHolder(
                    LoadingItemBinding.inflate(
                        layoutInflater,
                        parent,
                        attachToRoot
                    )
                )
            }
        }
    }

    class ErrorViewHolder(binding: ErrorItemBinding) : ItemViewHolder(binding.root) {

        private val _binding = binding

        override fun bind(loadState: LoadState) {
            require(loadState is LoadState.Error)
            _binding.errorMessage.text = loadState.error.localizedMessage
        }

        companion object {

            operator fun invoke(
                layoutInflater: LayoutInflater,
                parent: ViewGroup? = null,
                attachToRoot: Boolean = false
            ): ErrorViewHolder {

                return ErrorViewHolder(
                    ErrorItemBinding.inflate(
                        layoutInflater,
                        parent,
                        attachToRoot
                    )
                )
            }
        }
    }
}