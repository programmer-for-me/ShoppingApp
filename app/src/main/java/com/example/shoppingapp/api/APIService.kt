package com.example.shoppingapp.api

import com.example.shoppingapp.model.Product
import com.example.shoppingapp.model.ProductData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("/products")
    fun getAllProducts(): Call<ProductData>

    @GET("/products/{id}")
    fun getProduct(@Path("id") id: Int): Call<Product>
}