package com.mymovietheater.ui.category

import androidx.lifecycle.ViewModel
import com.mymovietheater.data.remote.MovieAPI
import com.mymovietheater.data.repositories.PagingRepository

class CategoryViewModel(
    repository: PagingRepository = PagingRepository(MovieAPI())
) : ViewModel() {
    val movies = repository.getMovies("popular")
}