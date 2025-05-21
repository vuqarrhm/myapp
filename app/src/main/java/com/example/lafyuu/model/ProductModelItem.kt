package com.example.lafyuu.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class ProductModelItem(
    @SerializedName("category")
    val category: Category?,
    @SerializedName("creationAt")
    val creationAt: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("images")
    val images: List<String?>?,
    @SerializedName("price")
    val price: BigDecimal?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?
):Parcelable