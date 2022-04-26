package com.example.myapplication.datn.repository.order

import androidx.lifecycle.LiveData
import com.example.myapplication.datn.model.entity.Order
import com.example.myapplication.datn.model.entity.Product

interface IOrderRepository {
    val cart: LiveData<Order>
    suspend fun update(item: Order)
    suspend fun insert(item: Order)
    suspend fun delete(item: Order)
}