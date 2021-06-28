package com.mymovietheater.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [DbMovie::class, DbCategory::class], version = 1, exportSchema = false)
@TypeConverters(CategoryConverter::class)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao

    companion object {
        @Volatile
        private var INSTANCE: LocalDataBase? = null;

        fun getDatabase(context: Context, scope:CoroutineScope): LocalDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(context, LocalDataBase::class.java, "local_db")
                        .addCallback(DbCallback(scope))
                        .build()
                INSTANCE = instance
                instance
            }
        }

        //callback to populate db
        private class DbCallback (private val scope: CoroutineScope)
            : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch { populateDb(database.categoryDao()) }
                }
            }

            private suspend fun populateDb(dao: CategoryDao) {
                //delete all the data first
                dao.deleteAll()

                //set default values
                dao.insert(DbCategory("now_playing", "Now Playing"))
                dao.insert(DbCategory("popular", "Popular"))
                dao.insert(DbCategory("top_rated", "Top Rated"))
                dao.insert(DbCategory("upcoming", "Upcoming"))
            }
        }
    }
}