package com.example.shoppingapp.api

import com.example.shoppingapp.model.Product
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface APIService {
    @GET("/products")
    fun getAllProducts(): Call<List<Product>>
}