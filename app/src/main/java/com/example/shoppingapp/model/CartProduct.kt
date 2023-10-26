package com.example.shoppingapp.model

data class CartProduct(
    val discountPercentage: Double,
    val discountedPrice: Int,
    val id: Int,
    val price: Int,
    val title: String,
    val total: Int
)