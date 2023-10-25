package com.example.shoppingapp.fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingapp.R
import com.example.shoppingapp.adapter.CategoryAdapter
import com.example.shoppingapp.adapter.FilterAdapter
import com.example.shoppingapp.api.APIClient
import com.example.shoppingapp.api.APIService
import com.example.shoppingapp.databinding.FragmentCategorilarBinding
import com.example.shoppingapp.model.Product
import com.example.shoppingapp.model.ProductData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CategorilarFragment : Fragment() {
//var recyclerView = R.id.categories_rv2;
var categoryname = R.id.category_name1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        var products = mutableListOf<Product>()
        val binding = FragmentCategorilarBinding.inflate(inflater, container, false)


        var layoutManager = GridLayoutManager(requireContext(),2, LinearLayoutManager.VERTICAL,false)
        val api = APIClient.getInstance().create(APIService::class.java)
        var categories = mutableListOf<String>()


        api.getAllCategories().enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                for (i in 0 until response.body()!!.size) {
                    categories.add(response.body()!!.get(i))
                }
                if (categories.isNotEmpty()) {
                    var adapter =CategoryAdapter(requireContext(),categories, object : CategoryAdapter.CategoryInterface {
                        override fun productOnClick(category: String) {
                            products.clear()

                            api.getProductsofCategory(category)
                                .enqueue(object : Callback<ProductData> {
                                    override fun onResponse(
                                        call: Call<ProductData>,
                                        response: Response<ProductData>
                                    ) {
                                        for (i in response.body()!!.products) {
                                            products.add(i)
                                        }

                                        var adapter = FilterAdapter(products, object : FilterAdapter.ProductInterface{
                                            override fun productOnClick(id: Int) {

                                            }
                                        })

                                        binding.productsRv.adapter = adapter
                                        binding.productsRv.layoutManager = layoutManager


                                    }

                                    override fun onFailure(
                                        call: Call<ProductData>,
                                        t: Throwable
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                })
                        }

                    })

                    var manager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    binding.categoriesRv.layoutManager = manager
                    binding.categoriesRv.adapter = adapter
                }


            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.d(ContentValues.TAG, "onFailure: $t")
            }

        })











                return binding.root
    }

}