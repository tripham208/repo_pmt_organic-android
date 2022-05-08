package com.example.myapplication.datn.repository.order

import androidx.lifecycle.LiveData
import com.example.myapplication.datn.model.entity.DetailOrder
import com.example.myapplication.datn.model.entity.Order
import com.example.myapplication.datn.model.entity.Product

interface IOrderRepository {
    val cart: LiveData<Order>
    val details: LiveData<List<DetailOrder>>
    suspend fun updateCart(item: Order)
    suspend fun insertCart(item: Order)
    suspend fun deleteCart()
    suspend fun updateDetail(item: DetailOrder)
    suspend fun insertDetail(item: DetailOrder)
    suspend fun deleteDetail(item: DetailOrder)
    suspend fun getOrderDetailsAPI(id: Int): List<DetailOrder>
    suspend fun getCartAPI(id: Int): Order
    suspend fun getHistoryAPI(id: Int): List<Order>
    suspend fun getDetailCart()
    suspend fun booking(order: Order):Order
}