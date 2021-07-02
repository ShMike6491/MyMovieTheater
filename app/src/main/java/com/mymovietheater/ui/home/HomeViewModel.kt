package com.mymovietheater.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mymovietheater.data.repositories.Category
import com.mymovietheater.data.repositories.Movie
import com.mymovietheater.data.repositories.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    val categories: LiveData<List<Category>> = repo.categories

    private val _latest = MutableLiveData<Movie?>()
    val latest: LiveData<Movie?> = _latest

    fun refresh() = viewModelScope.launch {
        repo.refreshCategories()
    }

    fun getLatest() = viewModelScope.launch {
        _latest.postValue(repo.requestLatest())
    }
}