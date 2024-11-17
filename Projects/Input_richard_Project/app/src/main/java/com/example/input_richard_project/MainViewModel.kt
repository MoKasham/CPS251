package com.example.input_richard_project

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // LiveData to store and observe the list
    val nameList = MutableLiveData<MutableList<String>>(mutableListOf())

    // Function to add a name to the list
    fun addName(name: String) {
        val updatedList = nameList.value ?: mutableListOf()
        updatedList.add(name)
        nameList.value = updatedList // Notify observers
    }
}