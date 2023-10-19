package com.example.shoppingapp.model

data class ProductItem (
    var image: String,
    var title: String,
    var discounted_price: Int,
    var price: Int,
    var discount: Int,
    var stars: Double
)