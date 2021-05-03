package com.mymovietheater.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mymovietheater.R
import com.mymovietheater.databinding.FragmentMainBinding
import com.mymovietheater.utils.getMovies

class HomeFragment : Fragment(R.layout.fragment_main) {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        val adapter = HomeAdapter()

        binding.apply {
            rvPopularSection.adapter = adapter
        }

        adapter.setMovies(getMovies())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}