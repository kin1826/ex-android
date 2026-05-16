package com.example.supple_ex.data

import com.example.supple_ex.dao.ProductDao
import com.example.supple_ex.model.Product
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val productDao: ProductDao) {
    val allProducts: Flow<List<Product>> = productDao.getAllProducts()

    suspend fun getProductById(id: Int): Product? = productDao.getProductById(id)

    suspend fun insert(product: Product) = productDao.insertProduct(product)

    suspend fun update(product: Product) = productDao.updateProduct(product)

    suspend fun delete(product: Product) = productDao.deleteProduct(product)
}
