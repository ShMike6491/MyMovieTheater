package com.mymovietheater.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mymovietheater.data.repositories.Category
import com.mymovietheater.data.repositories.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: Repository) : ViewModel() {

    val categories: LiveData<List<Category>> = repo.categories

    fun refresh() = viewModelScope.launch {
        repo.refreshCategories()
    }
}