package com.mymovietheater.data.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class MovieResponse(
    @field:SerializedName("results") val results: List<MovieModel>,
    @field:SerializedName("page") val page: Int?,
    @field:SerializedName("total_pages") val totalPages: Int?,
    @field:SerializedName("total_results") val totalResults: Int?
)

@Parcelize
data class MovieModel (
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("overview") val description: String,
    @field:SerializedName("adult") val adult: Boolean,
    @field:SerializedName("backdrop_path") val background: String,
    @field:SerializedName("poster_path") val poster: String,
    @field:SerializedName("release_date") val releaseDate: String,
    @field:SerializedName("popularity") val popularity: Double,
    @field:SerializedName("vote_average") val voteAverage: Double,
    @field:SerializedName("vote_count") val voteCount: Int,
    @field:SerializedName("genre_ids") val genreIds: List<Int>
) : Parcelable