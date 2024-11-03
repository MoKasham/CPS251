package com.example.recyclerview_project

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class recyclerview_adapter(var context : Context , var list : ArrayList<data>) : RecyclerView.Adapter<recyclerview_adapter.ViewHolderCatcher>() {
    inner class ViewHolderCatcher(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item_icon : ImageView = itemView.findViewById(R.id.item_icon)
        var title : TextView = itemView.findViewById(R.id.item_title)
        var detail : TextView = itemView.findViewById(R.id.item_details)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCatcher {
        var view = LayoutInflater.from(context).inflate(R.layout.item_layout , parent  , false)
        return ViewHolderCatcher(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolderCatcher, position: Int) {
        var myData = list.get(position)
        holder.item_icon.setImageResource(Integer.valueOf(myData.imageUrl))
        holder.title.setText(myData.title)
        holder.detail.setText(myData.description)


    }
}