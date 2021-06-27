package com.mymovietheater.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    fun getCategories(): LiveData<List<DbCategory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(genre: DbCategory)

    @Query("DELETE FROM category")
    suspend fun deleteAll()
}