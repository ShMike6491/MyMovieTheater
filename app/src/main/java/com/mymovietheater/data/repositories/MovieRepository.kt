package com.mymovietheater.data.repositories

import com.mymovietheater.data.remote.MovieAPI
import com.mymovietheater.data.remote.MovieResponse
import retrofit2.Callback

class MovieRepository(private val retrofitRes: MovieAPI) {
    fun getMovies(path: String, callback: Callback<MovieResponse>) {
        retrofitRes.getMovies(path, callback)
    }
}