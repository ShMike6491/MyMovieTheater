package com.mymovietheater.data.local

data class MovieModel (
    val id: Int,
    val title: String,
    val description: String,
    val adult: Boolean,
    val background: String,
    val poster: String,
    val releaseDate: String,
    val popularity: Double,
    val voteAverage: Double,
    val voteCount: Int,
    val genreIds: List<Int>
    )