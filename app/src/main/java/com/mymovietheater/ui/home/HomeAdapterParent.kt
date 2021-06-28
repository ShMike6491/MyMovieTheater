package com.mymovietheater.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mymovietheater.data.repositories.Category
import com.mymovietheater.databinding.ItemCategoryBinding

class HomeAdapterParent : RecyclerView.Adapter<HomeAdapterParent.ViewHolder>() {
    private var categoryList: List<Category> = emptyList()
    private val viewPool = RecyclerView.RecycledViewPool()

    fun setData(data: List<Category>) {
        categoryList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(categoryList[position])

    override fun getItemCount() = categoryList.size

    inner class ViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Category) {
            binding.apply {
                tvCategoryTitle.text = data.title
                rvMovies.adapter = HomeAdapterChild(data.movies ?: emptyList())
                rvMovies.setRecycledViewPool(viewPool)
            }
        }
    }
}