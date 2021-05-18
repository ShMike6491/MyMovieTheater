package com.mymovietheater.ui.home

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.mymovietheater.R
import com.mymovietheater.data.remote.MovieModel
import com.mymovietheater.databinding.FragmentHomeBinding
import com.mymovietheater.ui.details.DetailsFragment
import com.mymovietheater.ui.details.MOVIE_ARGUMENT_KEY
import com.mymovietheater.utils.AppState


class HomeFragment : Fragment(R.layout.fragment_home), FragmentHandler {
    private var _binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeViewModel
    private val adapter = HomeAdapter(this)
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        binding.rvSectionItems.adapter = adapter
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, { render(it) })
        viewModel.getMovies("popular")
    }

    private fun render(state: AppState?) {
        when (state) {
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

    override fun openFragment(model: MovieModel) {
        val manager = activity?.supportFragmentManager
        val bundle = Bundle()
        bundle.putParcelable(MOVIE_ARGUMENT_KEY, model)
        manager
            ?.beginTransaction()
            ?.replace(R.id.container, DetailsFragment.newInstance(bundle))
            ?.addToBackStack("")
            ?.commitAllowingStateLoss()

//        val fragmentManager: FragmentManager = childFragmentManager
//        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction
//            .replace((view!!.parent as ViewGroup).id, DetailsFragment.newInstance(model))
//            .addToBackStack(null)
//            .commit()

//        supportFragmentManager.beginTransaction()
//            .add(R.id.container, HomeFragment())
//            .commitNow()
    }
}