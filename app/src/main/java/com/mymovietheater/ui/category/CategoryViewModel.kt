package com.mymovietheater.ui.category

import androidx.lifecycle.ViewModel

class CategoryViewModel(
    repository: PagingRepository = PagingRepository()
) : ViewModel() {
    val movies = repository.getMovies("popular")
}