package com.mymovietheater.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mymovietheater.R
import com.mymovietheater.data.repositories.Category
import com.mymovietheater.data.repositories.Movie
import com.mymovietheater.databinding.ItemCategoryBinding
import com.mymovietheater.databinding.ItemLatestMovieBinding
import java.lang.IllegalArgumentException

class HomeAdapterParent(val navCallback: (Movie) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    private var categoryList: List<Category> = emptyList()
    private var poster: Movie? = null
    private val viewPool = RecyclerView.RecycledViewPool()

    fun setData(data: List<Category>) {
        categoryList = data

        val trendingMovie = data[0].movies?.get(0)
        poster = trendingMovie

        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = when (position) {
        0 -> VIEW_TYPE_POSTER
        else -> VIEW_TYPE_CATEGORIES
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_POSTER -> PosterHolder(
                ItemLatestMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            VIEW_TYPE_CATEGORIES -> ViewHolder(
                ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) holder.bind(categoryList[position - 1])
        if (holder is PosterHolder) holder.bind(poster)
    }

    override fun getItemCount() = categoryList.size + 1

    inner class ViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Category) {
            val adapter = HomeAdapterChild(data.movies ?: emptyList(), navCallback)

            binding.apply {
                tvCategoryTitle.text = data.title
                rvMovies.adapter = adapter
                rvMovies.setRecycledViewPool(viewPool)
            }
        }
    }

    inner class PosterHolder(private val binding: ItemLatestMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movie?) = data?.let {
            binding.apply {
                root.visibility = View.VISIBLE
                root.setOnClickListener { navCallback(data) }

                tvTitle.text = data.title

                Glide.with(ivPoster)
                    .load(data.poster)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(ivPoster)
            }
        }
    }

    companion object {
        const val VIEW_TYPE_POSTER = 1
        const val VIEW_TYPE_CATEGORIES = 2
    }
}