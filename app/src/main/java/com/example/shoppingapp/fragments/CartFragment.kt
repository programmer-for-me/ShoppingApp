package com.example.shoppingapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.shoppingapp.R
import com.example.shoppingapp.adapter.CartAdapter
import com.example.shoppingapp.api.APIClient
import com.example.shoppingapp.api.APIService
import com.example.shoppingapp.databinding.FragmentCartBinding
import com.example.shoppingapp.model.CartData
import com.example.shoppingapp.model.CartProduct
import com.example.shoppingapp.model.Product
import com.example.shoppingapp.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class CartFragment : Fragment() {

    var product: Product? = null
    private var id: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt("quantity")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCartBinding.inflate(inflater, container, false)
        val api = APIClient.getInstance().create(APIService::class.java)

        var user = User("a", "a", "x", 5, "https://robohash.org/hicveldicta.png?size=50x50&set=set1", "a", "q", "q")

        api.getProduct(id).enqueue(object  : Callback<Product>{
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                if (response.isSuccessful){
                    product = response.body()

                }
            }


            override fun onFailure(call: Call<Product>, t: Throwable) {
                Log.d("TAG", "onFailure: $t")
            }
        })

        api.getCart(user.id.toString()).enqueue(object : Callback<CartData> {
            @SuppressLint("SuspiciousIndentation", "NotifyDataSetChanged")
            override fun onResponse(call: Call<CartData>, response: Response<CartData>) {
                if (response.isSuccessful){
                    var cartList = response.body()!!.carts[0].products.toMutableList()
                    if (product != null){
                        val cartProduct = CartProduct(0.0, 0, product!!.id, product!!.price,  product!!.title, product!!.price)
                        cartList.add(0, cartProduct)
                        Log.d("SAID", product!!.brand)
                    }
                    var adapter = CartAdapter(cartList)
                    adapter.notifyDataSetChanged()
                    binding.cartRv.adapter = adapter
                    }

                }

            override fun onFailure(call: Call<CartData>, t: Throwable) {
                Log.d("TAG", "onFailure: $t")
            }
        })

        binding.back.setOnClickListener {
         findNavController().navigate(R.id.action_cartFragment_to_mainFragment)
        }


        return binding.root
    }


}