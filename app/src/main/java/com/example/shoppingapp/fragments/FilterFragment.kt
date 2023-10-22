package com.example.shoppingapp.fragments



import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioButton
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shoppingapp.R
import com.example.shoppingapp.adapter.FilterAdapter
import com.example.shoppingapp.api.APIClient
import com.example.shoppingapp.api.APIService
import com.example.shoppingapp.databinding.FragmentFilterBinding
import com.example.shoppingapp.databinding.FragmentMainBinding
import com.example.shoppingapp.model.Product
import com.example.shoppingapp.model.ProductData
import com.google.android.material.bottomsheet.BottomSheetDialog

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Collections


class FilterFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentFilterBinding.inflate(inflater, container, false)

        val api = APIClient.getInstance().create(APIService::class.java)

        var list=listOf<Product>()



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

        binding.bottomNavigation2.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.filter5 -> {
                    findNavController().navigate(R.id.categorilarFragment)
                    true
                }
                R.id.sortby1 -> {
                   showBottomSheet()
                    true

                }

                else -> {
                    false
                }
            }
        }



        binding.bottomNavigation2

        return binding.root
    }


    private fun showBottomSheet() {
        val bottomSheetView = layoutInflater.inflate(R.layout.share_bottom_sheet, null)
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
        dialog.setContentView(bottomSheetView)
        dialog.show()
    }



}

