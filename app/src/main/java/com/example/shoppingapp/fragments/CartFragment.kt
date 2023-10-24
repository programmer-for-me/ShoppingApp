package com.example.shoppingapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shoppingapp.R
import com.example.shoppingapp.adapter.CartAdapter
import com.example.shoppingapp.api.APIClient
import com.example.shoppingapp.api.APIService
import com.example.shoppingapp.databinding.FragmentCartBinding
import com.example.shoppingapp.model.CartData
import com.example.shoppingapp.model.CartProduct
import com.example.shoppingapp.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val ARG_PARAM1 = "product"
private const val ARG_PARAM2 = "quantity"


class CartFragment : Fragment() {

    var product: Product? = null
    private var quantity: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            product = it.getSerializable("product") as Product
            quantity = it.getInt("quantity")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCartBinding.inflate(inflater, container, false)
        val api = APIClient.getInstance().create(APIService::class.java)



//
//        api.getCart(user.id).enqueue(object : Callback<CartData> {
//            @SuppressLint("SuspiciousIndentation", "NotifyDataSetChanged")
//            override fun onResponse(call: Call<CartData>, response: Response<CartData>) {
//                if (response.isSuccessful){
//                    var cartList = response.body()!!.carts[0].products.toMutableList()
//                    if (product != null){
//
//                        val cartProduct = CartProduct(0.0, 0, product!!.id, product!!.price, quantity, product!!.title, product!!.price * quantity)
//                        cartList.add(0, cartProduct)
//                    }
//                    var adapter = CartAdapter(cartList)
//                    adapter.notifyDataSetChanged()
//                    binding.cartRv.adapter = adapter
//                    }
//
//                }
//
//            override fun onFailure(call: Call<CartData>, t: Throwable) {
//                Log.d("TAG", "onFailure: $t")
//            }
//        })

        binding.back.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, MainFragment())
                .commit()
        }

        return binding.root
    }


}