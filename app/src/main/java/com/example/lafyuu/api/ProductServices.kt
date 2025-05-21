package com.example.lafyuu.api

import com.example.lafyuu.model.Category
import com.example.lafyuu.model.ProductModelItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductServices {
    @GET("products")
    suspend fun getProduct(): Response<List<ProductModelItem>>
    @GET("categories")
    suspend fun getCategory():Response<List<Category>>

}