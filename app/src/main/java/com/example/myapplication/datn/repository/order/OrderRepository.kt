package com.example.myapplication.datn.repository.order

import androidx.lifecycle.LiveData
import com.example.myapplication.datn.database.AppAPI
import com.example.myapplication.datn.model.dao.DetailOrderDao
import com.example.myapplication.datn.model.dao.OrderDao
import com.example.myapplication.datn.model.entity.DetailOrder
import com.example.myapplication.datn.model.entity.Order
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
        api.updateCart(item.id, item)
    }

    override suspend fun insertCart(item: Order) {
        orderDao.insert(item)
    }

    override suspend fun deleteCart() {
        orderDao.deleteAll()
    }

    override suspend fun updateDetail(item: DetailOrder) {
        orderDetailOrder.updates(item)
        item.id?.let { api.updateDetail(it, item) }
        // getDetailCart()
    }

    override suspend fun insertDetail(item: DetailOrder) {
        val detail = orderDetailOrder.getItem(item.idProduct)
        if (detail != null) {
            detail.quantity += item.quantity
            updateDetail(detail)
        } else {
            api.addDetail(item)
            getDetailCart()
        }

    }

    override suspend fun getDetailCart() {
        cart.value?.id.let {
            if (it != null) {
                api.getDetails(it).forEach {
                    orderDetailOrder.insert(it)
                }
            }
        }
    }

    override suspend fun booking(order: Order):Order {
        val newCart = api.booking(order.id, order)
        orderDao.deleteAll()
        orderDao.insert(newCart)
        orderDetailOrder.deleteAll()
        return api.getOrder(order.id)

    }

    override suspend fun deleteDetail(item: DetailOrder) {
        orderDetailOrder.delete(item)
        item.id?.let { api.deleteDetail(it) }
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