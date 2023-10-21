package com.example.shoppingapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.FragmentFilterMenuBinding
import com.example.shoppingapp.databinding.FragmentMainBinding


class FilterMenu : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFilterMenuBinding.inflate(inflater, container, false)



//        loadFragment2(FilterFragment())
//
//        binding.bottomNavigation2.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.filter5 -> {
//                    false
//                }
//                R.id.sortby1 -> {
//                    false
//
//                }
//
//                else -> {
//                    false
//                }
//            }
//        }

        return binding.root
    }

//    private fun loadFragment2(fragment: Fragment) {
//        parentFragmentManager.beginTransaction().replace(R.id.container2, fragment).commit()
//    }

}