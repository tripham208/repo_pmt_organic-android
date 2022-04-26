package com.example.myapplication.datn.repository.order

import androidx.lifecycle.LiveData
import com.example.myapplication.datn.model.dao.OrderDao
import com.example.myapplication.datn.model.entity.Order
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderRepository @Inject constructor(
    private val orderDao: OrderDao
) : IOrderRepository {
    override val cart: LiveData<Order> = orderDao.getOrderToLiveData()

    override suspend fun update(item: Order) {
        orderDao.updates(item)
    }

    override suspend fun insert(item: Order) {
        orderDao.insert(item)
    }

    override suspend fun delete(item: Order) {
        orderDao.delete(item)
    }
}