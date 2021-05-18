package com.mymovietheater.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mymovietheater.R
import com.mymovietheater.data.remote.MovieModel
import com.mymovietheater.databinding.ItemCategoryFilmBinding
import com.mymovietheater.ui.MovieComparator

class CategoryAdapter :
    PagingDataAdapter<MovieModel, CategoryAdapter.ViewHolder>(MovieComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCategoryFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    class ViewHolder(private val binding: ItemCategoryFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: MovieModel) {
            binding.apply {
                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/w500${model.poster}")
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(ivPoster)

                tvTitle.text = model.title
                tvDesc.text = model.description
            }
        }
    }
}