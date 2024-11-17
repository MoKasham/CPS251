package com.example.input_richard_project

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NameAdapter(private var names: MutableList<String>) :
    RecyclerView.Adapter<NameAdapter.NameViewHolder>() {

    class NameViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_name, parent, false)
        return NameViewHolder(view)
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        holder.nameTextView.text = names[position]
    }

    override fun getItemCount(): Int = names.size

    // Update the list and refresh the adapter
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: MutableList<String>) {
        names = newList
        notifyDataSetChanged()
    }
}
