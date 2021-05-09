package com.mymovietheater.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mymovietheater.R
import com.mymovietheater.databinding.FragmentHomeBinding
import com.mymovietheater.utils.AppState

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeViewModel
    private val adapter = HomeAdapter()
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        binding.rvSectionItems.adapter = adapter
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, {render(it)})
        viewModel.getMovies("popular")
    }

    private fun render(state: AppState?) {
        when(state){
            is AppState.Error -> {
                Toast.makeText(requireContext(), "Smth went wrong", Toast.LENGTH_SHORT).show()
            }
            is AppState.Loading -> {
                Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
            }
            is AppState.Success -> {
                adapter.setMovies(state.model)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}