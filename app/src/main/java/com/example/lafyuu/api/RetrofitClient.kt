package com.example.lafyuu.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    fun createServices():ProductServices{
        val retrofit=Retrofit.Builder().baseUrl("https://api.escuelajs.co/api/v1/").
        addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit.create(ProductServices::class.java)
    }
}