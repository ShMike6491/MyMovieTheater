package com.mymovietheater.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mymovietheater.R
import com.mymovietheater.data.repositories.Movie
import com.mymovietheater.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()

    private lateinit var model: Movie

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)
        model = args.movie
        initViews()
    }

    private fun initViews() {
        binding.apply {
            Glide.with(ivPoster)
                .load("https://image.tmdb.org/t/p/w500${model.poster}")
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_error)
                .into(ivPoster)

            tvMovieTitle.text = model.title
            tvDescription.text = model.description
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}