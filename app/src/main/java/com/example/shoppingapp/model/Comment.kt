package com.example.shoppingapp.model

import java.io.Serializable

data class Comment(var heading:String, var review:String,var numberOfStars:Int,var userId:Int,var time:String):
    Serializable {
}