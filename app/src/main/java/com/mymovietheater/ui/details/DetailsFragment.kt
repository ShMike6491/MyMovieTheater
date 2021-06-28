package com.mymovietheater.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mymovietheater.R
import com.mymovietheater.data.remote.MovieModel
import com.mymovietheater.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var model: MovieModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)
        model = arguments?.getParcelable(MOVIE_ARGUMENT_KEY)!!
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

    companion object {
        fun newInstance(args: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}

const val MOVIE_ARGUMENT_KEY = "com.mymovietheater.ui.details.MOVIE_KEY"