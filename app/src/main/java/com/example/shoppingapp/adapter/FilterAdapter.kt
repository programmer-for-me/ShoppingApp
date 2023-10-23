package com.example.shoppingapp.adapter



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.shoppingapp.R
import com.example.shoppingapp.model.Product

import kotlin.math.roundToInt


class FilterAdapter(var list:List<Product>, var productInterface: ProductInterface): RecyclerView.Adapter<FilterAdapter.ProductHolder>(){
    class ProductHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView = itemView.findViewById(R.id.product_image)

        var itemTitle: TextView = itemView.findViewById(R.id.product_title)
        var itemPrice: TextView = itemView.findViewById(R.id.product_price)

        val discountPercentage : TextView = itemView.findViewById(R.id.textView)
        val rating: TextView = itemView.findViewById(R.id.stars)

        val discounted_price : TextView = itemView.findViewById(R.id.product_discounted_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        return ProductHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        var item = list[position]
        holder.itemImage.load(list[position].images[0])
        holder.itemTitle.text = item.title
        holder.itemPrice.text = "$" + item.price.toString()
        holder.discountPercentage.text = item.discountPercentage.toString() + "% OFF"
        holder.rating.text=item.rating.toString()
        holder.discounted_price.text = "$" + (item.price * (1-item.discountPercentage/100)).roundToInt().toString()

//        holder.itemView.setOnClickListener {
//            productInterface.productOnClick(list[position].id)
//        }

    }

    interface ProductInterface{
        fun productOnClick(product: Product)
    }
}