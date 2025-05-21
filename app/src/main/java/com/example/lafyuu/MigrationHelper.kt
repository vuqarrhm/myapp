package com.example.lafyuu.room

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object MigrationHelper {

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE products ADD COLUMN price REAL")
        }
    }

    val MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                """
                CREATE TABLE products_temp (
                    id INTEGER NOT NULL PRIMARY KEY,
                    title TEXT NOT NULL,
                    description TEXT,
                    updatedAt TEXT,
                    images TEXT,
                    price TEXT
                )
                """.trimIndent()
            )

            database.execSQL(
                """
                INSERT INTO products_temp (id, title, description, updatedAt, images, price)
                SELECT id, title, description, updatedAt, images, price FROM products
                """.trimIndent()
            )

            database.execSQL("DROP TABLE products")
            database.execSQL("ALTER TABLE products_temp RENAME TO products")
        }
    }
}
