package com.mymovietheater.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mymovietheater.R
import com.mymovietheater.data.repositories.Movie
import com.mymovietheater.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var adapter = HomeAdapterParent { callback(it) }
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        binding.rvCategories.adapter = adapter
        viewModel.categories.observe(viewLifecycleOwner) { adapter.setData(it) }
        viewModel.latest.observe(viewLifecycleOwner) { adapter.setMovie(it) }
        viewModel.getLatest()
    }

    private val callback = { movie: Movie ->
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movie)
        findNavController().navigate(action)
    }

//    private fun renderMovie(movie: Movie?) = movie?.let {
//        binding.ivPoster.visibility = View.VISIBLE
//
//        Glide.with(binding.ivPoster)
//            .load(movie.poster)
//            .centerCrop()
//            .transition(DrawableTransitionOptions.withCrossFade())
//            .error(R.drawable.ic_error)
//            .into(binding.ivPoster)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}