package com.example.shoppingapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R
import com.example.shoppingapp.model.Comment

class CommentAdapter(var commentList:MutableList<Comment>) : RecyclerView.Adapter<CommentAdapter.CommentHolder>(){
    class CommentHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var headimg: TextView = itemView.findViewById(R.id.heading_of_comment)
        var review: TextView = itemView.findViewById(R.id.comment_text)
        var nameAndTime: TextView = itemView.findViewById(R.id.comment_name_and_time)


        var star_1:View=itemView.findViewById(R.id.star_1)
        var star_2:View=itemView.findViewById(R.id.star_2)
        var star_3:View=itemView.findViewById(R.id.star_3)
        var star_4:View=itemView.findViewById(R.id.star_4)
        var star_5:View=itemView.findViewById(R.id.star_5)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder {
        return CommentHolder(LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false))
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
        var comment = commentList[position]
        holder.headimg.text = comment.heading
       holder.review.text=comment.review
        holder.nameAndTime.text=comment.time
        when (comment.numberOfStars) {
            1 -> {
                holder.star_1.resources.getColor(R.color.yellow)
            }
            2 -> {
                holder.star_1.resources.getColor(R.color.yellow)
                holder.star_2.resources.getColor(R.color.yellow)
            }
            3 -> {
                holder.star_1.resources.getColor(R.color.yellow)
                holder.star_2.resources.getColor(R.color.yellow)
                holder.star_3.resources.getColor(R.color.yellow)
            }
            4 -> {
                holder.star_1.resources.getColor(R.color.yellow)
                holder.star_2.resources.getColor(R.color.yellow)
                holder.star_3.resources.getColor(R.color.yellow)
                holder.star_4.resources.getColor(R.color.yellow)
            }
            5 -> {
                holder.star_1.resources.getColor(R.color.yellow)
                holder.star_2.resources.getColor(R.color.yellow)
                holder.star_3.resources.getColor(R.color.yellow)
                holder.star_4.resources.getColor(R.color.yellow)
                holder.star_5.resources.getColor(R.color.yellow)
            }
        }
    }



}