package com.example.supple_ex.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val productName: String,
    val productDescription: String,
    val productPrice: Double
)
