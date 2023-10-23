package com.example.shoppingapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R

class CategoryAdapter(val context: Context, val categories: List<String>, val categoryInterface: CategoryInterface) : RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {
    class CategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.category_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        return CategoryHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.name.text = categories[position]

        holder.itemView.setOnClickListener {
            categoryInterface.productOnClick(categories[position])
        }
    }

    interface CategoryInterface{
        fun productOnClick(name:String)
    }
}