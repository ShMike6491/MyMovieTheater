package com.mymovietheater.ui.category

import androidx.lifecycle.ViewModel
import com.mymovietheater.data.repositories.PagingRepository

class CategoryViewModel(
    repository: PagingRepository = PagingRepository()
) : ViewModel() {
    val movies = repository.getMovies("popular")
}