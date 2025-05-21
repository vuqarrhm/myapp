package com.example.lafyuu.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lafyuu.model.ProductItems
import com.example.lafyuu.model.ProductModelItem
@Dao
interface ProductsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductItems)

    @Query("SELECT * FROM products")
    fun getAllProducts(): LiveData<List<ProductItems>>

    @Query("DELETE FROM products WHERE id = :id")
    suspend fun deleteProduct(id: Int)
}

