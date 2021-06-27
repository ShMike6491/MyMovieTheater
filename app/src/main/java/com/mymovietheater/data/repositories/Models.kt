package com.mymovietheater.data.repositories

data class Category(
    val searchId: String,
    val title: String,
    val movies: List<Movie>? = null
)

data class Movie(
    val id: Int,
    val title: String,
    val description: String,
    val adult: Boolean,
    val poster: String,
    val releaseDate: String
)