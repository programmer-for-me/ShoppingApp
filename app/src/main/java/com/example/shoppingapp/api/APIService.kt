package com.example.shoppingapp.api

import com.example.shoppingapp.model.CartData
import com.example.shoppingapp.model.CommentData
import com.example.shoppingapp.model.Login
import com.example.shoppingapp.model.Product
import com.example.shoppingapp.model.ProductData
import com.example.shoppingapp.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("products")
    fun getAllProducts(): Call<ProductData>

    @GET("/carts/user/{id}")
    fun getCart(@Path("id") id: String): Call<CartData>


    @POST("/auth/login")
    fun login(@Body login: Login): Call<User>
    
    @GET("products/{id}")
    fun getProduct(@Path("id") id: Int): Call<Product>

    @GET("products/search")
    fun searchByName(@Query("q") name: String): Call<ProductData>

    @GET("/products/categories")
    fun getAllCategories():Call<List<String>>

    @GET("/products/category/{category}")
    fun getProductsofCategory(@Path("category") category: String):Call<ProductData>

    @GET("/comments/post/{id}")
    fun getCommentsOfProduct(@Body id:Int):Call<CommentData>
}