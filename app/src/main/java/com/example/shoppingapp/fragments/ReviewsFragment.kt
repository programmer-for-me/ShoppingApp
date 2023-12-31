package com.example.shoppingapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingapp.MyShared
import com.example.shoppingapp.R
import com.example.shoppingapp.adapter.CommentAdapter
import com.example.shoppingapp.api.APIClient
import com.example.shoppingapp.api.APIService
import com.example.shoppingapp.databinding.FragmentReviewsBinding
import com.example.shoppingapp.model.Comment
import com.example.shoppingapp.model.CommentData
import com.example.shoppingapp.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReviewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReviewsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    lateinit var commentObj:Comment
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding=FragmentReviewsBinding.inflate(layoutInflater,container,false)
        val api = APIClient.getInstance().create(APIService::class.java)
        var comment = mutableListOf<Comment>()
        val shared = MyShared.getInstance(requireContext())
        var product = shared.getProduct()
binding.close.setOnClickListener {
    findNavController().navigate(R.id.productInfoFragment)
}
        api.getCommentsOfProduct(product!!.id).enqueue(object : Callback<CommentData>{
            override fun onResponse(call: Call<CommentData>, response: Response<CommentData>) {
                if (response.isSuccessful && response.body() != null){
                    comment = response.body()!!.comments.toMutableList()
                    binding.reviewsRecyclerview.adapter = CommentAdapter(comment)
                    binding.reviewsRecyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                }
            }

            override fun onFailure(call: Call<CommentData>, t: Throwable) {
                Log.d("TAG", "onFailure: $t")
            }
        })
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ReviewsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReviewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}