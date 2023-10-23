package com.example.shoppingapp.fragments

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingapp.R
import com.example.shoppingapp.adapter.CategoriesAdapter
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
import java.util.Locale.Category


class CategorilarFragment : Fragment() {
//var recyclerView = R.id.categories_rv2;
var categoryname = R.id.category_name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var list=listOf<Product>()

        val binding = FragmentCategorilarBinding.inflate(inflater, container, false)

        val api = APIClient.getInstance().create(APIService::class.java)


        api.getAllProducts().enqueue(object : Callback<ProductData> {
            override fun onResponse(call: Call<ProductData>, response: Response<ProductData>) {
                list = response.body()?.products!!
                val mainAdapter = FilterAdapter(list, object : FilterAdapter.ProductInterface{
                    override fun productOnClick(product: Product) {
                        var bundle = bundleOf("id" to id)
                        findNavController().navigate(R.id.action_mainFragment_to_productInfoFragment, bundle)
                    }
                })
                var hourManager = GridLayoutManager(context, 2)
                binding.recycler.layoutManager = hourManager
                binding.recycler.adapter = mainAdapter
                Log.d(ContentValues.TAG, "onResponse: ${list}")

            }

            override fun onFailure(call: Call<ProductData>, t: Throwable) {
                Log.d(ContentValues.TAG, "onFailure: $t")
            }

        })
        var products = mutableListOf<Product>()
        var layoutManager = GridLayoutManager(requireContext(),2,LinearLayoutManager.HORIZONTAL,false)

        var categories = mutableListOf<String>()
        api.getAllCategories().enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                for (i in 0 until response.body()!!.size) {
                    categories.add(response.body()!!.get(i))
                }



                if (categories.isNotEmpty()) {
                    var adapter =
                        CategoriesAdapter(
                            categories,
                            requireContext(),
                            object : CategoriesAdapter.ItemClick {
                                override fun OnItemClick(category: String) {
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






//                                            Log.d(TAG, "onResponse: ${response.body()}")
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
                    binding.categoriesRv2.layoutManager = manager
                    binding.categoriesRv2.adapter = adapter
                }


            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }

        })


//        api.getAllCategories().enqueue(object : Callback<List<String>> {
//
//            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
//                var categories = response.body()!!
//                var categoryAdapter = CategoryAdapter(requireContext(), categories, object : CategoryAdapter.CategoryInterface{
//                    override fun productOnClick(name: String) {
//                        api.getCategoryProducts(name).enqueue(object : Callback<ProductData> {
//                            override fun onResponse(
//                                call: Call<ProductData>,
//                                response: Response<ProductData>
//                            ) {
//                                list = response.body()!!.products
//                                binding.recycler.adapter = FilterAdapter(list, object : FilterAdapter.ProductInterface{
//                                    override fun productOnClick(id: Int) {
//                                        var bundle = bundleOf("id" to id)
//                                        findNavController().navigate(R.id.action_categorilarFragment_to_productInfoFragment, bundle)
//
//                                    }
//                                })
//
//                                var hourManager = GridLayoutManager(context, 2)
//                                binding.recycler.layoutManager = hourManager
//
//                            }
//
//                            override fun onFailure(call: Call<ProductData>, t: Throwable) {
//                                TODO("Not yet implemented")
//                            }
//                        })
//                    }
//                })
//                binding.categoriesRv2.adapter = categoryAdapter
//                binding.categoriesRv2.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//
//            }
//
//            override fun onFailure(call: Call<List<String>>, t: Throwable) {
//                Log.d("TAG", "onFailure: $t")
//            }
//
//        })

                return binding.root
    }

}