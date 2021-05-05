package com.mymovietheater.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val TEMP_KEY = "8026e3d96dc6b594e89fca30bf14643a"

interface MovieService {
    @GET("movie/{collection}")
    fun getMovies(
        @Path("collection") collection: String,
        @Query("api_key") apiKey: String = TEMP_KEY
    ): Call<MovieResponse>
}