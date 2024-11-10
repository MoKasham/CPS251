package com.example.richard_rv_project

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class MainActivity : AppCompatActivity() {
    lateinit var main_rv: RecyclerView
    private val bookViewModel: BookViewModel by viewModels() // ViewModel to hold randomized data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_rv = findViewById(R.id.main_rv)

        // Prepare the static book data
        val bookData = BookData(
            titles = arrayOf(
                "The Great Gatsby", "1984", "To Kill a Mockingbird", "Venzvella", "Shapater", "Quarrel",
                "Pride and Prejudice", "Moby Dick", "War and Peace", "Crime and Punishment", "The Catcher in the Rye",
                "Brave New World", "The Odyssey", "The Hobbit", "Jane Eyre", "The Divine Comedy"
            ),
             imageResources = arrayOf(
                R.drawable.egineer, R.drawable.superstar, R.drawable.wifi, R.drawable.thinker,
        R.drawable.wrench, R.drawable.swimmer, R.drawable.world, R.drawable.egineer,
        R.drawable.wifi, R.drawable.fan, R.drawable.wrench, R.drawable.superstar,
        R.drawable.swimmer, R.drawable.wrench, R.drawable.thinker, R.drawable.fan
        ),
            descriptions = arrayOf(
                "A classic novel by F. Scott Fitzgerald.",
        "A dystopian novel by George Orwell.",
        "A novel about justice and race in the American South.",
        "Meaning to live life",
        "Hexadecimal actions",
        "Variable can be changed",
        "A story of love and social standing by Jane Austen.",
        "An epic tale of the sea by Herman Melville.",
        "A historical novel by Leo Tolstoy.",
        "A Russian psychological thriller by Fyodor Dostoevsky.",
        "A novel about adolescent angst by J.D. Salinger.",
        "A futuristic vision of society by Aldous Huxley.",
        "An ancient Greek epic about Odysseus's journey home.",
        "A fantasy adventure in Middle-earth by J.R.R. Tolkien.",
        "A Gothic romance by Charlotte Brontë.",
        "An allegorical journey through Hell, Purgatory, and Paradise by Dante Alighieri."
        ),

        )

        // Call randomize function from ViewModel
        bookViewModel.randomizeData(bookData)

        // Set up RecyclerView with randomized data from ViewModel
        main_rv.layoutManager = LinearLayoutManager(this)
        main_rv.adapter = BookAdapter(this, BookData(
            bookViewModel.randomizedTitles!!,
            bookViewModel.randomizedImages!!,
            bookViewModel.randomizedDescriptions!!
        ))
    }
}
