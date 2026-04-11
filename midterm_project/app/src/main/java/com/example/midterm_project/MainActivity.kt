package com.example.midterm_project.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm_project.R
import com.example.midterm_project.adapter.ProductAdapter
import com.example.midterm_project.api.ApiClient
import com.example.midterm_project.model.Product
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnAdd: Button
    private lateinit var adapter: ProductAdapter

    private val list = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // mapping
        recyclerView = findViewById(R.id.recyclerView)
        btnAdd = findViewById(R.id.btnAdd)

        // setup recycler
        adapter = ProductAdapter(
            list,
            onEdit = { product ->
                val intent = Intent(this, UpdateActivity::class.java)
                intent.putExtra("product", product)
                startActivity(intent)
            },
            onDelete = { product ->
                deleteProduct(product.id)
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // button add
        btnAdd.setOnClickListener {
            startActivity(Intent(this, AddNewActivity::class.java))
        }

        // load data
        loadProducts()
    }

    override fun onResume() {
        super.onResume()
        // quay lại từ Add → reload list
        loadProducts()
    }

    private fun loadProducts() {
        ApiClient.api.getProducts().enqueue(object : Callback<List<Product>> {

            override fun onResponse(
                call: Call<List<Product>>,
                response: Response<List<Product>>
            ) {
                if (response.isSuccessful) {

                    list.clear()
                    response.body()?.let {
                        list.addAll(it)
                    }

                    adapter.notifyDataSetChanged()

                    Log.d("API", "Load success")

                } else {
                    Log.e("API", "Response fail: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Log.e("API_ERROR", "Error: ${t.message}")
            }
        })
    }

    private fun deleteProduct(id: Int) {
        ApiClient.api.deleteProduct(id).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                loadProducts()
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("DELETE", t.message!!)
            }
        })
    }
}