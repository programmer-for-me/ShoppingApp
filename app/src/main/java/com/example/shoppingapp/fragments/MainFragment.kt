package com.example.shoppingapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.FragmentMainBinding


class MainFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater, container, false)



        loadFragment(ProductsFragment())

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(ProductsFragment())
                    true
                }
                R.id.categories -> {
                    false
                }
                R.id.orders -> {
                    false
                }
                R.id.profile -> {
                    false
                }
                else -> {
                    false
                }
            }
        }

        return binding.root
    }

    private fun loadFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }


}