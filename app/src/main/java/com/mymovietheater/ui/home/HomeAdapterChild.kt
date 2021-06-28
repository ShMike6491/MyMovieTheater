package com.mymovietheater.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mymovietheater.data.repositories.Movie
import com.mymovietheater.databinding.ItemMovieBinding

class HomeAdapterChild(private val movies: List<Movie>) :
    RecyclerView.Adapter<HomeAdapterChild.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(movies[position])

    override fun getItemCount() = movies.size

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movie) {
            binding.apply {
                tvItemTitle.text = data.title
                tvItemDesc.text = data.description
                Glide.with(itemView)
                    .load(data.poster)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(ivMovieItem)
            }
        }
    }
}