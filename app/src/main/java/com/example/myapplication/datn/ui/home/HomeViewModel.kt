package com.example.myapplication.datn.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.datn.model.entity.DetailOrder
import com.example.myapplication.datn.model.entity.Product
import com.example.myapplication.datn.repository.product.IProductRepository
import com.example.myapplication.datn.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val iProductRepository: IProductRepository
) :
    BaseViewModel() {
    val data: LiveData<List<Product>> = iProductRepository.allProduct
    val favorite: LiveData<List<Product>> = iProductRepository.favoriteProduct


    private val _search = MutableLiveData<List<Product>>()
    val search: LiveData<List<Product>>
        get() = _search

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    fun update(product: Product) = coroutineScope.launch {
        iProductRepository.update(product)
    }

    suspend fun getProduct(id: Int): Product? {
        var res: Product? = null
        withContext(coroutineScope.coroutineContext) {
            res = iProductRepository.getProductId(id)
        }
        return res
    }

    fun search(string: String) {
        coroutineScope.launch {
            val list = iProductRepository.search(string)
            _search.postValue(list)
        }
    }

    fun searchType(id: Int) {
        coroutineScope.launch {
            val list = iProductRepository.searchType(id)
            _search.postValue(list)
        }
    }

    fun getData() = coroutineScope.launch {
        iProductRepository.getFromAPI()
        _loading.postValue(false)
    }

}