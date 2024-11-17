package com.example.input_richard_project

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var inputName: EditText
    private lateinit var addButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NameAdapter

    // ViewModel to manage data across configuration changes
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        inputName = findViewById(R.id.inputName)
        addButton = findViewById(R.id.addButton)
        recyclerView = findViewById(R.id.recyclerView)

        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NameAdapter(viewModel.nameList.value ?: mutableListOf())
        recyclerView.adapter = adapter

        // Observe the LiveData from the ViewModel
        viewModel.nameList.observe(this, Observer { updatedList ->
            adapter.updateData(updatedList)
        })

        // Add button click listener
        addButton.setOnClickListener {
            val name = inputName.text.toString().trim()
            if (name.isNotEmpty()) {
                addNameWithDelay(name)
                inputName.text.clear()
            }
        }
    }
    private fun addNameWithDelay(name: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val delayTime = Random.nextLong(1000, 10000) // Random delay 1-10 seconds
            withContext(Dispatchers.IO) {
                delay(delayTime)
            }
            viewModel.addName("The name is $name and delay was (${delayTime}milliseconds)") // Add name with delay time to ViewModel
        }
    }
}