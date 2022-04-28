package com.example.myapplication.datn.repository.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.myapplication.datn.database.AppAPI
import com.example.myapplication.datn.model.dao.ProductDao
import com.example.myapplication.datn.model.entity.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val productDao: ProductDao,
    private val api: AppAPI
) : IProductRepository {
    override val allProduct: LiveData<List<Product>> = productDao.getAllToFlow().asLiveData()
    override val favoriteProduct: LiveData<List<Product>> = productDao.getFavorite().asLiveData()
    override suspend fun update(product: Product) {
        productDao.update(product)
    }

    override suspend fun getProductId(id: Int): Product {
        return productDao.getProduct(id)
    }

    override suspend fun getFromAPI(){
        val list = api.getProducts()
        list.forEach {
            productDao.insert(it)
        }
    }
}