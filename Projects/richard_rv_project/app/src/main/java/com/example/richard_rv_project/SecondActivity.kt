package com.example.richard_rv_project

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        // Retrieve data from the intent
        val titlez = intent.getStringExtra("title")
        val descriptionz = intent.getStringExtra("description")
        val imageResourcez = intent.getIntExtra("imageResource", 0) // Default to 0 if not found


        var title = findViewById<TextView>(R.id.second_title)
        var imgView = findViewById<ImageView>(R.id.imgView_second)
        var description = findViewById<TextView>(R.id.description_second)

        // Set the data to the views
        title.text = titlez
        description.text = descriptionz
        imgView.setImageResource(imageResourcez)



    }
}