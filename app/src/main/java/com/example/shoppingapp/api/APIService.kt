package com.example.shoppingapp.api

import com.example.shoppingapp.model.Product
import com.example.shoppingapp.model.ProductData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("products")
    fun getAllProducts(): Call<ProductData>

    @GET("products/{id}")
    fun getProduct(@Path("id") id: Int): Call<Product>

    @GET("products/search")
    fun searchByName(@Query("q") name: String): Call<ProductData>

    @GET("products/categories")
    fun getAllCategories(): Call<List<String>>

    @GET("products/category/{category}")
    fun getCategoryProducts(): Call<Product>
}