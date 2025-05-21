package com.example.lafyuu.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lafyuu.model.BookmarkItems

@Dao
interface BookmarkDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookmark: BookmarkItems)

    @Query("SELECT * FROM bookmarks")
    fun getAll(): LiveData<List<BookmarkItems>>

    @Query("DELETE FROM bookmarks WHERE id = :id")
    suspend fun delete(id: Int)
}
