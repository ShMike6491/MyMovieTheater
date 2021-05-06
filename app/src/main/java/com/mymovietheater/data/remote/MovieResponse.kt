package com.mymovietheater.data.remote

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @field:SerializedName("results") val results: List<MovieModel>,
    @field:SerializedName("page") val page: Int?,
    @field:SerializedName("total_pages") val totalPages: Int?,
    @field:SerializedName("total_results") val totalResults: Int?
)