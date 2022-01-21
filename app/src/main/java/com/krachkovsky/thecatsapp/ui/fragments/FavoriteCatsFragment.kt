package com.krachkovsky.thecatsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.krachkovsky.thecatsapp.databinding.FragmentFavoriteCatsListBinding
import org.koin.android.ext.android.bind

class FavoriteCatsFragment : Fragment() {

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


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}