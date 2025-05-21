package com.example.lafyuu.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lafyuu.model.BasketItems

@Dao
interface BasketDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(basket: BasketItems)

    @Query("SELECT * FROM basket")
    fun getAll(): LiveData<List<BasketItems>>

    @Query("DELETE FROM basket WHERE id = :id")
    suspend fun delete(id: Int)
}
