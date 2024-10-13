package com.rich.billtip

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.editTextNumber)
        val textView = findViewById<TextView>(R.id.textView)
        btn.setOnClickListener {
            val bill = editText.text.toString().toDoubleOrNull()
            textView.text = if (bill != null && bill > 0) {
                val tip10 = bill + bill * 0.1
                val tip15 = bill + bill * 0.15
                val tip20 = bill * 1.2
                StringBuilder().apply {
                    appendLine("the tips is as follow")
                    appendLine("10% = $tip10")
                    appendLine("15% = $tip15")
                    append("20% = $tip20")
                }
            } else {
                "YOU MUST ENTER A BILL AMOUNT"
            }
        }
    }
}