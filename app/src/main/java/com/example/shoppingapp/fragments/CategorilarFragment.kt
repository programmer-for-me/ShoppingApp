package com.example.shoppingapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingapp.R
import com.example.shoppingapp.adapter.CategoryAdapter
import com.example.shoppingapp.api.APIClient
import com.example.shoppingapp.api.APIService
import com.example.shoppingapp.databinding.FragmentCategorilarBinding
import com.example.shoppingapp.databinding.FragmentFilterBinding
import com.example.shoppingapp.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CategorilarFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val binding = FragmentCategorilarBinding.inflate(inflater, container, false)

        val api = APIClient.getInstance().create(APIService::class.java)
//        var list=listOf<String>()
//
//
//        var categoryAdapter2 = CategoryAdapter(requireContext(), list)
//        var hour2Manager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//        binding.categoriesRv2.adapter = categoryAdapter2
//        binding.categoriesRv2.layoutManager = hour2Manager





        api.getAllCategories().enqueue(object : Callback<List<String>> {

            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                var categories = response.body()!!
                var categoryAdapter = CategoryAdapter(requireContext(), categories)
                binding.categoriesRv2.adapter = categoryAdapter
                binding.categoriesRv2.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.d("TAG", "onFailure: $t")
            }

        })







        return binding.root
    }

}