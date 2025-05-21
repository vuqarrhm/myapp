package com.example.lafyuu

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {

    // List<String?> -> String çevirmə
    @TypeConverter
    fun fromStringList(value: List<String?>?): String {
        return value?.filterNotNull()?.joinToString(",") ?: ""
    }

    // String -> List<String?> çevirmə
    @TypeConverter
    fun toStringList(value: String): List<String?> {
        return if (value.isNotEmpty()) {
            value.split(",").map { it.trim() }
        } else {
            emptyList()
        }
    }

    // BigDecimal -> String
    @TypeConverter
    fun fromBigDecimal(value: BigDecimal?): String? {
        return value?.toPlainString()
    }

    // String -> BigDecimal
    @TypeConverter
    fun toBigDecimal(value: String?): BigDecimal? {
        return value?.let { BigDecimal(it) }
    }
}
