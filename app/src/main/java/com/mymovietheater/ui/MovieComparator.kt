package com.mymovietheater.ui

import androidx.recyclerview.widget.DiffUtil
import com.mymovietheater.data.remote.MovieModel

class MovieComparator : DiffUtil.ItemCallback<MovieModel>() {
    override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel) = oldItem == newItem
}