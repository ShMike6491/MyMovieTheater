package com.mymovietheater.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mymovietheater.R
import com.mymovietheater.data.remote.MovieModel
import com.mymovietheater.databinding.ItemMovieBinding

class HomeAdapter :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private var data: List<MovieModel> = emptyList()

    fun setMovies(data: List<MovieModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val binding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                //TODO finish it later
                Toast.makeText(it.context, "Detail window will be opened", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        fun bind(model: MovieModel) {
            binding.apply {
                Glide.with(ivMovieItem)
                    .load("https://image.tmdb.org/t/p/w500${model.poster}")
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(ivMovieItem)

                tvItemTitle.text = model.title
                tvItemDesc.text = model.description
            }
        }
    }
}