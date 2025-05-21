package com.example.lafyuu.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "basket")
data class BasketItems(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String?,
    val updatedAt: String?,
    val images: List<String?>?,
    val price: BigDecimal?,
    var quantity: Int = 1 // Default olaraq 1 qoyulur
)
