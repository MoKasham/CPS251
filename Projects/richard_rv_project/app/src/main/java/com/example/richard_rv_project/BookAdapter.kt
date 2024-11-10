package com.example.richard_rv_project

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(var context : Context , var array: BookData) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title : TextView = itemView.findViewById(R.id.title)
        var imgView : ImageView = itemView.findViewById(R.id.img_view)
        var description : TextView = itemView.findViewById(R.id.description)
        var index : TextView = itemView.findViewById(R.id.index)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout , parent , false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return array.titles.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.index.text = position.toString()
        holder.title.text = array.titles[position]
        holder.description.text = array.descriptions[position]
        holder.imgView.setImageResource(array.imageResources[position])
        holder.index.visibility = View.INVISIBLE
        // Set an onClickListener to pass data and start SecondActivity
        holder.itemView.setOnClickListener {
            val intent = Intent(context, SecondActivity::class.java).apply {
                // Add extras to the intent to pass the selected item’s data
                putExtra("title", array.titles[position])
                putExtra("description", array.descriptions[position])
                putExtra("imageResource", array.imageResources[position])
            }
            ContextCompat.startActivity(context, intent, null)
        }
    }
}