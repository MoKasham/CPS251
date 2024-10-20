package com.rich.savedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    fun addName(newName: String) {
        if (newName.isEmpty()) {
            _name.value = ""
        } else {
            _name.value = StringBuilder().apply {
                appendLine(newName)
                _name.value?.let { append(it) }
            }.toString()
        }

    }

}