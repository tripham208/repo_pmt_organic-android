package com.example.myapplication.datn.repository.product

import androidx.lifecycle.LiveData
import com.example.myapplication.datn.model.entity.Product

interface IProductRepository {

    val allProduct: LiveData<List<Product>>
    val favoriteProduct: LiveData<List<Product>>
    suspend fun update(product: Product)
    suspend fun getProductId(id: Int): Product
    suspend fun getFromAPI()
    suspend fun search(string: String): List<Product>
    suspend fun searchType(id: Int): List<Product>


}