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
import android.text.TextWatcher
import android.text.Editable
import com.bumptech.glide.Glide

class AddNewActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtDesc: EditText
    private lateinit var edtPrice: EditText
    private lateinit var edtImage: EditText
    private lateinit var btnSubmit: Button
    private lateinit var imgPreview: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        edtName = findViewById(R.id.edtName)
        edtDesc = findViewById(R.id.edtDesc)
        edtPrice = findViewById(R.id.edtPrice)
        edtImage = findViewById(R.id.edtImage)
        imgPreview = findViewById(R.id.imgPreview)
        btnSubmit = findViewById(R.id.btnSubmit)

        edtImage.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                val url = s.toString().trim()

                if (url.isNotEmpty() && url.startsWith("http")) {

                    Glide.with(this@AddNewActivity)
                        .load(url)
                        .placeholder(android.R.drawable.ic_menu_gallery)
                        .error(android.R.drawable.ic_delete)
                        .into(imgPreview)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        btnSubmit.setOnClickListener {
            uploadProduct()
        }
    }

    private fun uploadProduct() {
        val name = edtName.text.toString().trim()
        val desc = edtDesc.text.toString().trim()
        val price = edtPrice.text.toString().trim()
        val imageUrl = edtImage.text.toString().trim()

        if (name.isEmpty() || desc.isEmpty() || price.isEmpty() || imageUrl.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
            return
        }

        ApiClient.api.insertProduct(name, desc, price, imageUrl)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@AddNewActivity, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@AddNewActivity, "Lỗi server: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("AddNewActivity", "Lỗi kết nối: ${t.message}")
                    Toast.makeText(this@AddNewActivity, "Lỗi kết nối: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
    }
}