package com.example.shoppingapp.model

data class CartData(
    var carts: List<Cart>,
    val limit: Int,
    val skip: Int,
    val total: Int
)
