package com.example.shoppingapp.fragments



import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shoppingapp.adapter.FilterAdapter
import com.example.shoppingapp.api.APIClient
import com.example.shoppingapp.api.APIService
import com.example.shoppingapp.databinding.FragmentFilterBinding
import com.example.shoppingapp.databinding.FragmentMainBinding
import com.example.shoppingapp.model.Product
import com.example.shoppingapp.model.ProductData

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class FilterFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentFilterBinding.inflate(inflater, container, false)

        val api = APIClient.getInstance().create(APIService::class.java)
//        val l = Login("hbingley1", "CQutx25i8")
//        api.login(l).enqueue(object : Callback<User> {
//            override fun onResponse(call: Call<User>, response: Response<User>) {
//                if (response.isSuccessful && response.body() != null) {
//                    Log.d(TAG, "onResponse: ${response.body()?.username}")
//                }
//            }
//
//            override fun onFailure(call: Call<User>, t: Throwable) {
//                Log.d(TAG, "onFailure: $t")
//            }
//
//        })
//
//        api.getAllProducts().enqueue(object : Callback<ProductData> {
//            override fun onResponse(call: Call<ProductData>, response: Response<ProductData>) {
//                if (response.isSuccessful && response.body() != null)
//                    Log.d(TAG, "onResponse: ${response.body()?.products?.get(1)?.description}")
//            }
//
//            override fun onFailure(call: Call<ProductData>, t: Throwable) {
//                Log.d(TAG, "onFailure: $t")
//            }
//
//        })
//
//        api.getProduct(7).enqueue(object : Callback<Product> {
//            override fun onResponse(call: Call<Product>, response: Response<Product>) {
//                if (response.isSuccessful && response.body() != null)
//                    Log.d(TAG, "onResponse: ${response.body()?.title}")
//            }
//
//            override fun onFailure(call: Call<Product>, t: Throwable) {
//                Log.d(TAG, "onFailure: $t")
//            }
//
//        })

        var list=listOf<Product>()


//        var mainAdapter = MainAdapter(list)
//        var hourManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        binding.recycler.adapter = mainAdapter
//        binding.recycler.layoutManager = hourManager


        api.getAllProducts().enqueue(object : Callback<ProductData> {
            override fun onResponse(call: Call<ProductData>, response: Response<ProductData>) {
                list = response.body()?.products!!
                val mainAdapter = FilterAdapter(list)
                var hourManager = GridLayoutManager(context, 2)
                binding.recycler.layoutManager = hourManager
                binding.recycler.adapter = mainAdapter
                Log.d(TAG, "onResponse: ${list}")

            }

            override fun onFailure(call: Call<ProductData>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }

        })

        api.getAllProducts().enqueue(object : Callback<ProductData> {
            override fun onResponse(call: Call<ProductData>, response: Response<ProductData>) {
                if (response.isSuccessful && response.body() != null)
                    Log.d(TAG, "onResponse: ${response.body()?.products?.get(1)?.description}")
            }

            override fun onFailure(call: Call<ProductData>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }

        })
        binding.sv.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText !=null) {
                    api.searchByName(newText).enqueue(object :Callback<ProductData>{
                        override fun onResponse(
                            call: Call<ProductData>,
                            response: Response<ProductData>
                        ) {
                            list = response.body()?.products!!
                            val mainAdapter = FilterAdapter(list)
                            var hourManager = GridLayoutManager(context, 2)
                            binding.recycler.layoutManager = hourManager
                            binding.recycler.adapter = mainAdapter
                            Log.d(TAG, "onResponse: ${list}")



                        }

                        override fun onFailure(call: Call<ProductData>, t: Throwable) {
                            Log.d(TAG, "onFailure: $t")
                        }

                    })
                    return true
                }
                return false
            }

        })
        return binding.root
    }



}

