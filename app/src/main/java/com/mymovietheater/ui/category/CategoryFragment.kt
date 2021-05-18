package com.mymovietheater.ui.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mymovietheater.R
import com.mymovietheater.databinding.FragmentCategoryPageBinding
import com.mymovietheater.ui.home.HomeViewModel

class CategoryFragment : Fragment(R.layout.fragment_category_page) {
    private var _binding: FragmentCategoryPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CategoryViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCategoryPageBinding.bind(view)
        val adapter = CategoryAdapter()
        binding.apply {
            rvCategoty.setHasFixedSize(true)
            rvCategoty.adapter = adapter
        }
        viewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        viewModel.movies.observe(viewLifecycleOwner, {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}