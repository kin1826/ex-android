package com.example.lesson7

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val tv = findViewById<TextView>(R.id.tvHello)
        val name = intent.getStringExtra("NAME")

        tv.text = "Hello $name 👋"
    }
}