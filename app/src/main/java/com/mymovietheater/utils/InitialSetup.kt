package com.mymovietheater.utils

import com.mymovietheater.data.remote.MovieModel

fun getMovies(): List<MovieModel> {
    val list: MutableList<MovieModel> = mutableListOf()
    val samplePoster = "https://www.questionpro.com/blog/wp-content/uploads/2018/11/Sample-768x460.jpg"

    for (i in 1..10) {
        list.add(
            MovieModel(
                i,
                "Title $i",
                "description $i",
                false,
                samplePoster,
                samplePoster,
                "2007-1-2",
                12.4,
                3.4,
                1234,
                listOf(1, 2, 3, 4)
            )
        )
    }
    return list
}
