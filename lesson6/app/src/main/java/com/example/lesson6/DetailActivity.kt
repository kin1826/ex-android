package com.example.lesson6

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val img = findViewById<ImageView>(R.id.imgAvatar)
        val txtId = findViewById<TextView>(R.id.txtId)
        val txtName = findViewById<TextView>(R.id.txtName)
        val txtAge = findViewById<TextView>(R.id.txtAge)
        val txtMajor = findViewById<TextView>(R.id.txtMajor)

        val id = intent.getIntExtra("id", 0)
        val name = intent.getStringExtra("name")
        val age = intent.getIntExtra("age", 0)
        val major = intent.getStringExtra("major")
        val avatar = intent.getIntExtra("avatar", 0)

        img.setImageResource(avatar)
        txtId.text = "ID: $id"
        txtName.text = "Name: $name"
        txtAge.text = "Age: $age"
        txtMajor.text = "Major: $major"
    }
}