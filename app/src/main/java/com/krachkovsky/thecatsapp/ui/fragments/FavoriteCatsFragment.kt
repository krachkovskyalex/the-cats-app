package com.krachkovsky.thecatsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.addRepeatingJob
import androidx.paging.LoadState
import com.bumptech.glide.Glide
import com.krachkovsky.thecatsapp.R
import com.krachkovsky.thecatsapp.adapters.CatsLoaderStateAdapter
import com.krachkovsky.thecatsapp.adapters.CatsPagingDataAdapter
import com.krachkovsky.thecatsapp.databinding.FragmentFavoriteCatsListBinding
import com.krachkovsky.thecatsapp.viewmodels.CatsViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteCatsFragment : Fragment() {

    private val adapter = CatsPagingDataAdapter(R.drawable.ic_unfavorite) {
        viewModel.deleteFavoriteCat(it)
    }
    private val viewModel: CatsViewModel by viewModel()

    private var _binding: FragmentFavoriteCatsListBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentFavoriteCatsListBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            rvFavorite.adapter = adapter.withLoadStateFooter(CatsLoaderStateAdapter())
            adapter.addLoadStateListener { state ->
                val refreshState = state.refresh
                if (state.refresh != LoadState.Loading) {
                    Glide.with(root)
                        .load(R.drawable.progress_main)
                        .into(ivLoadingFavoriteCats)
                }
                rvFavorite.isVisible = refreshState != LoadState.Loading
                frameLayoutLoading.isVisible = refreshState == LoadState.Loading
                if (refreshState is LoadState.Error) {
                    rvFavorite.isVisible = false
                    frameLayoutError.isVisible = true
                    tvFavoriteCatsError.text = refreshState.error.localizedMessage
                }
            }
        }

        addRepeatingJob(Lifecycle.State.STARTED) {
            viewModel.favoriteCatsList
                .collectLatest(adapter::submitData)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}