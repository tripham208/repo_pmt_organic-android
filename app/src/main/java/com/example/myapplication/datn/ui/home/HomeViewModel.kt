package com.example.myapplication.datn.ui.home

import androidx.lifecycle.LiveData
import com.example.myapplication.datn.model.entity.Product
import com.example.myapplication.datn.repository.product.IProductRepository
import com.example.myapplication.datn.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val iProductRepository: IProductRepository) :
    BaseViewModel() {
    val data: LiveData<List<Product>> = iProductRepository.allProduct
    val favorite: LiveData<List<Product>> = iProductRepository.favoriteProduct

    fun update(product: Product) = coroutineScope.launch {
        iProductRepository.update(product)
    }
}