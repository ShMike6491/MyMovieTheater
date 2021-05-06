package com.mymovietheater.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mymovietheater.R
import com.mymovietheater.data.remote.MovieModel
import com.mymovietheater.databinding.FragmentMainBinding
import com.mymovietheater.utils.AppState

class HomeFragment : Fragment(R.layout.fragment_main) {
    private var _binding: FragmentMainBinding? = null
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: HomeAdapter
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        adapter = HomeAdapter()
        binding.rvPopularSection.adapter = adapter
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getMovies("now_playing")
    }

    private fun renderData(it: AppState?) {
        when (it) {
            is AppState.Loading -> {
                Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()

            }
            is AppState.Success -> {
                renderView(it.model)
            }
            is AppState.Error -> {
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun renderView(model: List<MovieModel>) {
        adapter.setMovies(model)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}