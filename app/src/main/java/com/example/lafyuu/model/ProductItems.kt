package com.example.lafyuu.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "products")
data class ProductItems(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Avtomatik artımlı ID
    val title: String,
    val description: String?,
    val updatedAt: String?,
    val images: List<String?>?,
    val price: BigDecimal? // Yeni `price` sahəsi
)

