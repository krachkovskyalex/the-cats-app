package com.krachkovsky.thecatsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.krachkovsky.thecatsapp.adapters.CatsListAdapter
import com.krachkovsky.thecatsapp.databinding.FragmentAllCatsListBinding
import com.krachkovsky.thecatsapp.viewmodels.CatsViewModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class AllCatsFragment : Fragment() {

    private val adapter: CatsListAdapter by inject()
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

        binding.rvMain.adapter = adapter

        viewModel.catsList.observe(viewLifecycleOwner, {
            if (it.isEmpty()) {
                viewModel.loadData()
            }
            adapter.submitList(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}