package com.mymovietheater.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Movie::class, Genre::class], version = 1, exportSchema = false)
@TypeConverters(GenreConverter::class)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun genreDao(): GenreDao

    companion object {
        @Volatile
        private var INSTANCE: LocalDataBase? = null;

        fun getDatabase(context: Context): LocalDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(context, LocalDataBase::class.java, "local_db").build()
                INSTANCE = instance
                instance
            }
        }
    }
}