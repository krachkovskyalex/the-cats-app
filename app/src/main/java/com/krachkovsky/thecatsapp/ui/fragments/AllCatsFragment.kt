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
import com.krachkovsky.thecatsapp.databinding.FragmentAllCatsListBinding
import com.krachkovsky.thecatsapp.viewmodels.CatsViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.viewmodel.ext.android.viewModel

class AllCatsFragment : Fragment() {

    private val adapter = CatsPagingDataAdapter(R.drawable.ic_favorite) {
        viewModel.saveFavoriteCat(it)
    }
    private val viewModel: CatsViewModel by viewModel()

    private var _binding: FragmentAllCatsListBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentAllCatsListBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            rvMain.adapter = adapter.withLoadStateFooter(CatsLoaderStateAdapter())
            adapter.addLoadStateListener { state ->
                val refreshState = state.refresh
                if (state.refresh != LoadState.Loading) {
                    Glide.with(root)
                        .load(R.drawable.progress_main)
                        .into(ivLoadingAllCats)
                }
                rvMain.isVisible = refreshState != LoadState.Loading
                frameLayoutLoading.isVisible = refreshState == LoadState.Loading
                if (refreshState is LoadState.Error) {
                    rvMain.isVisible = false
                    frameLayoutError.isVisible = true
                    tvAllCatsError.text = showErrorText(refreshState.error.localizedMessage)
                }
            }
        }

        addRepeatingJob(Lifecycle.State.STARTED) {
            viewModel.catsList
                .collectLatest(adapter::submitData)
        }
    }

    private fun showErrorText(error: String?): String {
        return "Network tell: " +
                error +
                "\n" +
                "Please check your internet connection " +
                "and tap to All icon with cat on the bottom..."
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}