package com.example.shoppingapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shoppingapp.api.APIClient
import com.example.shoppingapp.api.APIService
import com.example.shoppingapp.databinding.FragmentProductInfoBinding
import com.example.shoppingapp.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductInfoFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentProductInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductInfoBinding.inflate(layoutInflater)
        val api = APIClient.getInstance().create(APIService::class.java)

        api.getProduct(1).enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                if (response.isSuccessful && response.body() != null)
                    Log.d("AAA", "onResponse: ${response.body()?.title}")
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                Log.d("AAB", "onFailure: $t")
            }

        })
        return binding.root
    }


}