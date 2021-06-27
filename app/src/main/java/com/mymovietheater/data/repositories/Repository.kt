package com.mymovietheater.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mymovietheater.data.local.DbCategory
import com.mymovietheater.data.local.LocalDataBase
import com.mymovietheater.data.local.asDomainCategory
import com.mymovietheater.data.remote.MovieService
import com.mymovietheater.data.remote.asDatabaseMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository (val database: LocalDataBase, val network: MovieService) {
    companion object {
        const val TAG = "Repository"
    }

    val categories: LiveData<List<Category>> =
        Transformations.map(database.categoryDao().getCategories()) { it.asDomainCategory() }

    suspend fun refreshCategories(): Unit = withContext(Dispatchers.IO) {
        Log.d(TAG, "Refreshing categories data")
        val savedCategories: List<Category>? = categories.value

        savedCategories?.forEach { category ->
            //get data from the network
            val response = network.getCategories(category.searchId).asDatabaseMovies()

            //refresh existing data with the network response
            database.categoryDao().insert(
                DbCategory(category.searchId, category.title, response)
            )
        }
    }
}