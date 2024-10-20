package com.rich.savedata

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.rich.savedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.name.observe(this) {
            if (it.isEmpty()){
                binding.textView.text = getString(R.string.no_name_entered)
            }else{
                binding.textView.text = it
            }
        }
        binding.button.setOnClickListener {
            viewModel.addName(binding.editTextName.text.toString())
            binding.editTextName.text.clear()
        }

    }
}