package com.example.midterm_project.activity

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.midterm_project.R
import com.example.midterm_project.api.ApiClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import com.example.midterm_project.model.Product

class UpdateActivity : AppCompatActivity() {

    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        product = intent.getSerializableExtra("product") as Product

        val edtName = findViewById<EditText>(R.id.edtName)
        val edtDesc = findViewById<EditText>(R.id.edtDesc)
        val edtPrice = findViewById<EditText>(R.id.edtPrice)
        val edtImage = findViewById<EditText>(R.id.edtImage)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)

        edtName.setText(product.productName)
        edtDesc.setText(product.description)
        edtPrice.setText(product.price.toString())
        edtImage.setText(product.image)

        btnUpdate.setOnClickListener {
            updateProduct(
                product.id,
                edtName.text.toString(),
                edtDesc.text.toString(),
                edtPrice.text.toString(),
                edtImage.text.toString()
            )
        }
    }

    private fun updateProduct(
        id: Int,
        name: String,
        desc: String,
        price: String,
        image: String
    ) {
        ApiClient.api.updateProduct(id, name, desc, price, image)
            .enqueue(object : Callback<ResponseBody> {

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    finish()
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {}
            })
    }
}