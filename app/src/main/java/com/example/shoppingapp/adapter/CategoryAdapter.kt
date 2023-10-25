package com.example.shoppingapp.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R
import com.google.android.material.card.MaterialCardView

class CategoryAdapter(val context: Context, val categories: List<String>, val categoryInterface: CategoryInterface) : RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {
    var current = 0

    class CategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name = itemView.findViewById<TextView>(R.id.category_name1)
        var cardView = itemView.findViewById<MaterialCardView>(R.id.category_mcv)
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

        val item = categories.get(position)
//        holder.name.text = categories[position]


        if (position == 0) {
            holder.name.text = "All"
            holder.cardView.setCardBackgroundColor(context.resources.getColor(R.color.purple))
            holder.name.setTextColor(context.resources.getColor(R.color.white))

        }
        else {
            holder.name.text = categories[position -1].capitalize()
        }



        if ( position == current) {
            holder.cardView.setCardBackgroundColor(context.resources.getColor(R.color.purple))
            holder.name.setTextColor(context.resources.getColor(R.color.white))
        } else {
            holder.cardView.setCardBackgroundColor(context.resources.getColor(R.color.white))
            holder.name.setTextColor(context.resources.getColor(R.color.purple))
        }



        holder.cardView.setOnClickListener {
            if (position != current && position !=0) {

                notifyItemChanged(current)
                current = position
                notifyItemChanged(current)
                categoryInterface.productOnClick(categories[position-1])
            }




//            categoryRecyclerView.visibility = View.GONE
        }










    }

    interface CategoryInterface{
        fun productOnClick(name:String)
    }
}