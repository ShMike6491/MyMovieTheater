package com.mymovietheater.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.mymovietheater.data.remote.MovieAPI
import com.mymovietheater.data.remote.MoviePagingSource
import com.mymovietheater.data.remote.MovieService

class PagingRepository {
    fun getMovies(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(MovieService.service, query) }
        ).liveData
}