package com.mymovietheater.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mymovietheater.data.local.CategoryDao
import com.mymovietheater.data.local.DbCategory
import com.mymovietheater.data.local.asDomainCategory
import com.mymovietheater.data.remote.MovieService
import com.mymovietheater.data.remote.asDatabaseMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(private val dao: CategoryDao, private val service: MovieService) {
    companion object {
        const val TAG = "Repository"
    }

    val categories: LiveData<List<Category>> =
        Transformations.map(dao.getCategories()) { it.asDomainCategory() }

    suspend fun refreshCategories(): Unit = withContext(Dispatchers.IO) {
        Log.d(TAG, "Refreshing categories data")
        val savedCategories: List<Category>? = categories.value

        savedCategories?.forEach { category ->
            Log.d(TAG, "Server request")
            //get data from the network
            val response = service.getCategories(category.searchId).asDatabaseMovies()
            Log.d(TAG, "Saving network data $response")

            //refresh existing data with the network response
            dao.insert(
                DbCategory(category.searchId, category.title, response)
            )
        }
    }
}