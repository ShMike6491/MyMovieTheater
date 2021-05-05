package com.mymovietheater.data.remote

import com.google.gson.annotations.SerializedName
import com.mymovietheater.data.local.MovieModel

data class MovieResponse(
    @field:SerializedName("results") val results: List<MovieModel>,
    @field:SerializedName("page") val page: Int?,
    @field:SerializedName("total_pages") val totalPages: Int?,
    @field:SerializedName("total_results") val totalResults: Int?
)