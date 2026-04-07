package com.example.lesson8

import androidx.recyclerview.widget.RecyclerView
import com.example.lesson8.model.*
import com.example.lesson8.api.*
import android.view.*
import android.widget.*

class PostAdapter : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private var list = listOf<Post>()

    fun submitList(data: List<Post>) {
        list = data
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.txtTitle)
        val body: TextView = view.findViewById(R.id.txtBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = list[position]
        holder.title.text = post.title
        holder.body.text = post.body
    }

    override fun getItemCount(): Int = list.size
}