package com.mymovietheater.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @GET("movie/{collection}")
    suspend fun getPagedMovies(
        @Path("collection") collection: String,
        @Query("api_key") apiKey: String = TEMP_KEY,
        @Query("page") page: Int
    ): MovieResponse

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"

        private val logger =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

        private val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: MovieService = retrofit.create(MovieService::class.java)
    }
}