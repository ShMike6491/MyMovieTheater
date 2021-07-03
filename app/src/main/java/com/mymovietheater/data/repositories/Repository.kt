package com.mymovietheater.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mymovietheater.data.local.CategoryDao
import com.mymovietheater.data.local.DbCategory
import com.mymovietheater.data.local.asDomainCategory
import com.mymovietheater.data.remote.MovieService
import com.mymovietheater.data.remote.asDatabaseMovies
import com.mymovietheater.data.remote.asDomainMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val dao: CategoryDao,
    private val service: MovieService
) {
    companion object {
        const val TAG = "Repository"
    }

    val categories: LiveData<List<Category>> =
        Transformations.map(dao.getCategories()) { it.asDomainCategory() }

    suspend fun refreshCategories(): Unit = withContext(Dispatchers.IO) {
        Log.d(TAG, "Refreshing categories data")

        val categories = mutableListOf<Category>()
        categories.add(Category("now_playing", "Now Playing"))
        categories.add(Category("popular", "Popular"))
        categories.add(Category("top_rated", "Top Rated"))
        categories.add(Category("upcoming", "Upcoming"))

        categories.forEach { category ->
            Log.d(TAG, "Server request")
            //get data from the network
            val response = service.getCategories(category.searchId).asDatabaseMovies()

            //refresh existing data with the network response
            dao.insert(
                DbCategory(category.searchId, category.title, response)
            )
        }
    }
}