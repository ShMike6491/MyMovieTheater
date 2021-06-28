package com.mymovietheater.data.remote

import com.google.gson.annotations.SerializedName
import com.mymovietheater.data.local.DbMovie

data class MovieResponse(
    @field:SerializedName("results") val results: List<MovieModel>,
    @field:SerializedName("page") val page: Int?,
    @field:SerializedName("total_pages") val totalPages: Int?,
    @field:SerializedName("total_results") val totalResults: Int?
)

data class MovieModel(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("overview") val description: String,
    @field:SerializedName("adult") val adult: Boolean?,
    @field:SerializedName("poster_path") val poster: String?,
    @field:SerializedName("release_date") val releaseDate: String?
)

/**
 * Extension function to convert network data layer to database layer
 */
fun MovieResponse.asDatabaseMovies(): List<DbMovie> = results.map {
    DbMovie(
        id = it.id,
        title = it.title,
        description = it.description,
        adult = it.adult,
        poster = it.poster,
        releaseDate = it.releaseDate
    )
}