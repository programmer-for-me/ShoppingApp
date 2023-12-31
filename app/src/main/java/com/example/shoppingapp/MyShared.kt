package com.example.shoppingapp

import android.content.Context
import android.content.SharedPreferences
import com.example.shoppingapp.model.Product
import com.example.shoppingapp.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MyShared private constructor(context: Context){
    private val shared: SharedPreferences = context.getSharedPreferences("data", 0)
    private val edit: SharedPreferences.Editor = shared.edit()
    private val gson  = Gson()

    companion object{
        private var instance :MyShared? = null
        fun getInstance(context: Context):MyShared{
            if (instance == null){
                instance = MyShared(context)
            }
            return instance!!
        }
    }

    fun setUser(user: User){
        val data  = gson.toJson(user)
        edit.putString("user", data).apply()
    }

    fun logOut(){
        edit.putString("user", "").apply()
    }
    fun getUser():User?{
        val data = shared.getString("user", "")
        if (data == "") return null
        val typeToken = object : TypeToken<User>() {}.type
        return gson.fromJson(data, typeToken)
    }

    fun setProduct(product: Product){
        val data = gson.toJson(product)
        edit.putString("product", data).apply()
    }

    fun getProduct(): Product? {
        val data = shared.getString("product", "")
        if (data == "") return null
        val typeToken = object : TypeToken<Product>() {}.type
        return gson.fromJson(data, typeToken)
    }

    fun deleteProduct(){
        edit.putString("product","").apply()
    }

}