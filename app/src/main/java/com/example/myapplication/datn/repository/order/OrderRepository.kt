package com.example.myapplication.datn.repository.order

import androidx.lifecycle.LiveData
import com.example.myapplication.datn.database.AppAPI
import com.example.myapplication.datn.model.dao.DetailOrderDao
import com.example.myapplication.datn.model.dao.OrderDao
import com.example.myapplication.datn.model.entity.DetailOrder
import com.example.myapplication.datn.model.entity.Order
import com.example.myapplication.datn.utils.Logger
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderRepository @Inject constructor(
    private val orderDao: OrderDao,
    private val orderDetailOrder: DetailOrderDao,
    private val api: AppAPI
) : IOrderRepository {
    override val cart: LiveData<Order> = orderDao.getOrderToLiveData()
    override val details: LiveData<List<DetailOrder>> = orderDetailOrder.getAllToLiveData()

    override suspend fun updateCart(item: Order) {
        orderDao.updates(item)
    }

    override suspend fun insertCart(item: Order) {
        orderDao.insert(item)
    }

    override suspend fun deleteCart(item: Order) {
        orderDao.delete(item)
    }

    override suspend fun updateDetail(item: DetailOrder) {
        orderDetailOrder.updates(item)
    }

    override suspend fun insertDetail(item: DetailOrder) {

            if (
                orderDetailOrder.getItem(item.idsanpham) != null
            ) {
                val old = orderDetailOrder.getItem(item.idsanpham)
                val new = item.copy(
                    soluong = old.soluong + item.soluong
                )
                updateDetail(new)
            }else{
                orderDetailOrder.insert(item)
            }
    }

    override suspend fun deleteDetail(item: DetailOrder) {
        orderDetailOrder.delete(item)
    }

    override suspend fun getOrderDetailsAPI(id: Int): List<DetailOrder> {
        return api.getDetails(id)
    }

    override suspend fun getCartAPI(id: Int): Order {
        return api.getCart(id)
    }

    override suspend fun getHistoryAPI(id: Int): List<Order> {
       return api.getOrdersByIdUser(id)
    }




}