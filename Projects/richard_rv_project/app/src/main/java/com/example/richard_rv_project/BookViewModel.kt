package com.example.richard_rv_project

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class BookViewModel : ViewModel() {
    var randomizedTitles: Array<String>? = null
    var randomizedImages: Array<Int>? = null
    var randomizedDescriptions: Array<String>? = null

    fun randomizeData(bookData: BookData) {
        if (randomizedTitles == null) {
            // If data is not already randomized, do it now.
            randomizedTitles = bookData.titles.copyOf()
            randomizedImages = bookData.imageResources.copyOf()
            randomizedDescriptions = bookData.descriptions.copyOf()

            // Shuffle the arrays to randomize the data.
            val indices = randomizedTitles!!.indices.shuffled()
            randomizedTitles = indices.map { randomizedTitles!![it] }.toTypedArray()
            randomizedImages = indices.map { randomizedImages!![it] }.toTypedArray()
            randomizedDescriptions = indices.map { randomizedDescriptions!![it] }.toTypedArray()
        }
    }
}
