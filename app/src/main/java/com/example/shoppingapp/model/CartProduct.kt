package com.example.shoppingapp.model

data class CartProduct(
    val discountPercentage: Int,
    val discountedPrice: Int,
    val id: Int,
    val price: Int,
    val quantity: Int,
    val title: String,
    val total: Int
)