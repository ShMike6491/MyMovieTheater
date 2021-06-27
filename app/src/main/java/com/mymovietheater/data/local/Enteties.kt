package com.mymovietheater.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mymovietheater.data.repositories.Category
import com.mymovietheater.data.repositories.Movie

@Entity(tableName = "category")
data class DbCategory(
    @PrimaryKey val searchId: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "movies") val movies: List<DbMovie>? = null
)

@Entity(tableName = "movie")
data class DbMovie(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "adult") val adult: Boolean,
    @ColumnInfo(name = "poster") val poster: String,
    @ColumnInfo(name = "release_date") val releaseDate: String
)

/**
 * Data type conversion to save Json to the database
 */
class CategoryConverter {
    @TypeConverter
    fun fromGenreMovies(movies: List<DbMovie?>?): String? {
        if (movies == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<DbMovie?>?>() {}.type
        return gson.toJson(movies, type)
    }

    @TypeConverter
    fun toGenreMovies(movieString: String?): List<DbMovie>? {
        if (movieString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<DbMovie?>?>() {}.type
        return gson.fromJson<List<DbMovie>>(movieString, type)
    }
}

/**
 * Extension function to convert database data layer to the application layer
 */
fun List<DbCategory>.asDomainCategory(): List<Category> = map {
    Category(
        searchId = it.searchId,
        title = it.title,
        movies = it.movies?.asDomainMovie()
    )
}

/**
 * Extension function to convert database data layer to the application layer
 */
fun List<DbMovie>.asDomainMovie(): List<Movie> = map {
    Movie(
        id = it.id,
        title = it.title,
        description = it.description,
        adult = it.adult,
        poster = it.poster,
        releaseDate = it.releaseDate
    )
}