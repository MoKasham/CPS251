package com.example.recyclerview_project

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var recyclerview : RecyclerView
    var arrayList : ArrayList<data> = ArrayList()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview = findViewById(R.id.recyclerview)

        arrayList.add(data(R.drawable.androidengineer, "Chapter Seven", "Item Seven Details"))
        arrayList.add(data(R.drawable.androidsayinghi, "Chapter Five", "Item Five Details"))
        arrayList.add(data(R.drawable.androidwithwrench, "Chapter Nine", "Item Nine Details"))
        arrayList.add(data(R.drawable.androidengineer, "Chapter One", "Item One Details"))
        arrayList.add(data(R.drawable.androidwithwrench, "Chapter Three", "Item Three Details"))
        arrayList.add(data(R.drawable.androidsayinghi, "Chapter Two", "Item Two Details"))
        arrayList.add(data(R.drawable.androidwithwrench, "Chapter Six", "Item Six Details"))
        arrayList.add(data(R.drawable.androidengineer, "Chapter Eight", "Item Eight Details"))
        arrayList.add(data(R.drawable.androidsayinghi, "Chapter Four", "Item Four Details"))
        arrayList.add(data(R.drawable.androidwithwrench, "Chapter Ten", "Item Ten Details"))

        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = recyclerview_adapter(this@MainActivity , arrayList)




    }
}