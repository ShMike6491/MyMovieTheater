package com.mymovietheater.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mymovietheater.App
import com.mymovietheater.R
import com.mymovietheater.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var adapter = HomeAdapterParent()
    private val viewModel: HomeViewModel by viewModels {
        ViewModelFactory((activity?.application as App).repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        binding.rvCategories.adapter = adapter
        viewModel.categories.observe(viewLifecycleOwner) { adapter.setData(it) }
        binding.fabRefresh.setOnClickListener { viewModel.refresh() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}