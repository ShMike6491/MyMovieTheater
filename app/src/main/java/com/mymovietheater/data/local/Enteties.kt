package com.mymovietheater.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity
data class Genre(
    @PrimaryKey val searchId: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "movies") val movies: List<Movie>? = null
)

@Entity
data class Movie(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "adult") val adult: Boolean,
    @ColumnInfo(name = "poster") val poster: String,
    @ColumnInfo(name = "release_date") val releaseDate: String
)

class GenreConverter {
    @TypeConverter
    fun fromGenreMovies(movies: List<Movie?>?): String? {
        if (movies == null) { return null }
        val gson = Gson()
        val type = object : TypeToken<List<Movie?>?>() {}.type
        return gson.toJson(movies, type)
    }

    @TypeConverter
    fun toGenreMovies(movieString: String?): List<Movie>? {
        if (movieString == null) { return null }
        val gson = Gson()
        val type = object : TypeToken<List<Movie?>?>() {}.type
        return gson.fromJson<List<Movie>>(movieString, type)
    }
}