package com.mymovietheater.data.repositories

import com.mymovietheater.data.remote.MovieResponse
import com.mymovietheater.data.remote.MovieService
import retrofit2.Callback

class MovieRepository(private val service: MovieService) {
    fun getMovies(path: String, callback: Callback<MovieResponse>) {
        service.getMovies(path).enqueue(callback)
    }
}