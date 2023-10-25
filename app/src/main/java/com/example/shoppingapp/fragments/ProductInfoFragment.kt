package com.example.shoppingapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.shoppingapp.R
import com.example.shoppingapp.adapter.ViewPagerAdapter
import com.example.shoppingapp.api.APIClient
import com.example.shoppingapp.api.APIService
import com.example.shoppingapp.databinding.FragmentProductInfoBinding
import com.example.shoppingapp.model.CartProduct
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

        val addCart_list = mutableListOf<CartProduct>()

        if (arguments?.containsKey("id") == true) {
            api.getProduct(arguments?.getInt("id")!!)
                .enqueue(object : Callback<Product> {
                    override fun onResponse(call: Call<Product>, response: Response<Product>) {
                        if (response.isSuccessful && response.body() != null)
                            binding.titlePager.text = response.body()?.title.toString()
                        binding.pagerDescription.text = response.body()?.description.toString()
                        binding.pagerRating.text = response.body()?.rating.toString()
                        binding.brand.text = "• Brand - ${response.body()?.brand}"
                        binding.categoryNameDetailsPager.text =
                            "• Category - ${response.body()?.category}"
                        binding.detailsNamePager.text = "• Name - ${response.body()?.title}"
                        binding.brand.text = "• Brand - ${response.body()?.brand}"
                        binding.discountPercentagePager.text =
                            "${response.body()?.discountPercentage.toString()}%"

                        binding.originalPricePager.text = "$${response.body()?.price.toString()}"
                        binding.discountedPricePager.text =
                            (response.body()?.price!!.toInt() - (response.body()?.discountPercentage!!.toInt() * response.body()?.price!!) / 100).toString()
                        binding.stockPager.text = response.body()?.stock.toString()

                        binding.pagerItemRating.text = response.body()?.rating.toString()
                        binding.productPager.adapter = ViewPagerAdapter(response.body()?.images!!)

                        binding.addCart.setOnClickListener {
                            val text = "Added Successfully!"
                            val duration = Toast.LENGTH_SHORT

                            val toast = Toast.makeText(context, text, duration) // in Activity
                            toast.show()
                        }
                        binding.productPager.registerOnPageChangeCallback(object :
                            ViewPager2.OnPageChangeCallback() {
                            override fun onPageScrolled(
                                position: Int,
                                positionOffset: Float,
                                positionOffsetPixels: Int
                            ) {
                                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                                changeColor()
                            }

                            override fun onPageSelected(position: Int) {
                                super.onPageSelected(position)
                            }

                            override fun onPageScrollStateChanged(state: Int) {
                                super.onPageScrollStateChanged(state)
                                changeColor()
                            }

                            fun changeColor() {
                                when (binding.productPager.currentItem) {

                                    0 -> {
                                        binding.iv1.setImageResource(R.color.blue)
                                        binding.iv2.setImageResource(R.color.grey)
                                        binding.iv3.setImageResource(R.color.grey)
                                        binding.iv4.setImageResource(R.color.grey)
                                        binding.iv5.setImageResource(R.color.grey)
                                    }

                                    1 -> {
                                        binding.iv1.setImageResource(R.color.grey)
                                        binding.iv2.setImageResource(R.color.blue)
                                        binding.iv3.setImageResource(R.color.grey)
                                        binding.iv4.setImageResource(R.color.grey)
                                        binding.iv5.setImageResource(R.color.grey)

                                    }

                                    2 -> {
                                        binding.iv1.setImageResource(R.color.grey)
                                        binding.iv2.setImageResource(R.color.grey)
                                        binding.iv3.setImageResource(R.color.blue)
                                        binding.iv4.setImageResource(R.color.grey)
                                        binding.iv5.setImageResource(R.color.grey)

                                    }

                                    3 -> {
                                        binding.iv1.setImageResource(R.color.grey)
                                        binding.iv2.setImageResource(R.color.grey)
                                        binding.iv3.setImageResource(R.color.grey)
                                        binding.iv4.setImageResource(R.color.blue)
                                        binding.iv5.setImageResource(R.color.grey)

                                    }

                                    4 -> {
                                        binding.iv1.setImageResource(R.color.grey)
                                        binding.iv2.setImageResource(R.color.grey)
                                        binding.iv3.setImageResource(R.color.grey)
                                        binding.iv4.setImageResource(R.color.grey)
                                        binding.iv5.setImageResource(R.color.blue)

                                    }
                                }
                            }

                        })
                        Log.d("AAA", "onResponse: ${response.body()?.title}")
                    }

                    override fun onFailure(call: Call<Product>, t: Throwable) {
                        Log.d("AAB", "onFailure: $t")
                    }

                })
        }
        binding.infoBack.setOnClickListener {
            findNavController().navigate(R.id.action_productInfoFragment_to_productsFragment)
        }
        return binding.root
    }


}