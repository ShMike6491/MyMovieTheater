package com.mymovietheater.utils

import com.mymovietheater.data.local.MovieModel

sealed class AppState {
    data class Success(val model: List<MovieModel>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}