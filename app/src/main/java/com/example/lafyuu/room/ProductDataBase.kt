package com.example.lafyuu.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lafyuu.model.ProductItems
import com.example.lafyuu.model.BookmarkItems
import com.example.lafyuu.model.BasketItems
import com.example.lafyuu.Converters

@Database(
    entities = [ProductItems::class, BookmarkItems::class, BasketItems::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productsDAO(): ProductsDAO
    abstract fun bookmarkDAO(): BookmarkDAO
    abstract fun basketDAO(): BasketDAO
}
