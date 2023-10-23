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

private const val ARG_PARAM1 = "param1"

class ProductInfoFragment : Fragment() {
    private var param1: Int? = null
    private lateinit var binding: FragmentProductInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductInfoBinding.inflate(layoutInflater)
        val api = APIClient.getInstance().create(APIService::class.java)
        if (arguments?.containsKey("id") == true) {
            api.getProduct(arguments?.getInt("id")!!)
                .enqueue(object : Callback<Product> {
                    override fun onResponse(call: Call<Product>, response: Response<Product>) {
                        if (response.isSuccessful && response.body() != null)
                            Log.d("AAA", "onResponse: ${response.body()?.title}")
                    }

                    override fun onFailure(call: Call<Product>, t: Throwable) {
                        Log.d("AAB", "onFailure: $t")
                    }

                })
        }
        return binding.root
    }


}