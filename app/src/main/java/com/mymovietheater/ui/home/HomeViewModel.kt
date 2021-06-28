package com.mymovietheater.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mymovietheater.data.repositories.Category
import com.mymovietheater.data.repositories.Repository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class HomeViewModel(private val repo: Repository) : ViewModel() {

    val categories: LiveData<List<Category>> = repo.categories

    fun refresh() = viewModelScope.launch {
        repo.refreshCategories()
    }
}

class ViewModelFactory(private val repo: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED CAST")
            return HomeViewModel(repo) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModelClass")
        }
    }
}