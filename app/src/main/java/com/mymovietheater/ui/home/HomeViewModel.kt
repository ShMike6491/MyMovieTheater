package com.mymovietheater.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mymovietheater.data.remote.MovieAPI
import com.mymovietheater.data.remote.MovieResponse
import com.mymovietheater.data.repositories.MovieRepository
import com.mymovietheater.utils.AppState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: MovieRepository = MovieRepository(MovieAPI()),
) : ViewModel() {

    fun getLiveData(): MutableLiveData<AppState> = liveData

    fun getMovies(path: String) {
        liveData.value = AppState.Loading
        repository.getMovies(path, callback)
    }

    private val callback = object : Callback<MovieResponse> {
        override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
            val res: MovieResponse? = response.body()
            liveData.postValue(
                if (response.isSuccessful && res != null) {
                    checkResponse(res)
                } else {
                    AppState.Error(Throwable("server error"))
                }
            )
        }

        override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
            liveData.postValue(AppState.Error(Throwable(t.message)))
        }

        private fun checkResponse(res: MovieResponse): AppState {
            val results = res.results
            return if (results == null || results.isEmpty()) {
                AppState.Error(Throwable("corrupted data"))
            } else {
                AppState.Success(res.results)
            }
        }
    }
}