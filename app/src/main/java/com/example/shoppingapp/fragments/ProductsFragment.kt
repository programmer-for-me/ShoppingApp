package com.example.shoppingapp.fragments

import android.annotation.SuppressLint
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
import com.example.shoppingapp.adapter.CategoryAdapter
import com.example.shoppingapp.adapter.FilterAdapter
import com.example.shoppingapp.api.APIClient
import com.example.shoppingapp.api.APIService
import com.example.shoppingapp.databinding.FragmentProductsBinding
import com.example.shoppingapp.model.ProductData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProductsBinding.inflate(inflater, container, false)
        val api = APIClient.getInstance().create(APIService::class.java)

//        binding.productsSearch?.setOnClickListener( {
//            findNavController().navigate(R.id.filterFragment)
//        })
        binding.productsSearch2?.setOnClickListener({
            findNavController().navigate(R.id.filterFragment)
        })
        binding.viewAll?.setOnClickListener({
            findNavController().navigate(R.id.categorilarFragment)
        })



        api.getAllProducts().enqueue(object : Callback<ProductData> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<ProductData>, response: Response<ProductData>) {
                var products = response.body()!!.products
                var productsAdapter = FilterAdapter(products, object : FilterAdapter.ProductInterface{
                    override fun productOnClick(id: Int) {
                        var bundle = bundleOf(Pair("id", id))
                        findNavController().navigate(R.id.action_productsFragment_to_productInfoFragment, bundle)
                    }

                })
                binding.productsRv.adapter = productsAdapter
                binding.productsRv.layoutManager = GridLayoutManager(requireContext(), 2)
            }

            override fun onFailure(call: Call<ProductData>, t: Throwable) {
                Log.d("TAG", "onFailure: $t")
            }

        })
        api.getAllCategories().enqueue(object : Callback<List<String>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                var categories = response.body()!!
                var categoryAdapter = CategoryAdapter(requireContext(), categories)
                binding.categoriesRv.adapter = categoryAdapter
                binding.categoriesRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.d("TAG", "onFailure: $t")
            }

        })
        return binding.root
    }


}