package com.example.myapplication.datn.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.datn.model.entity.DetailOrder
import com.example.myapplication.datn.model.entity.Order
import com.example.myapplication.datn.repository.order.IOrderRepository
import com.example.myapplication.datn.repository.user.IUserRepository
import com.example.myapplication.datn.ui.base.BaseViewModel
import com.google.firebase.auth.ktx.userProfileChangeRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val iOrderRepository: IOrderRepository,
    private val iUserRepository: IUserRepository
) :
    BaseViewModel() {
    val cart: LiveData<Order> = iOrderRepository.cart
    val details: LiveData<List<DetailOrder>> = iOrderRepository.details

    private val _history = MutableLiveData<List<Order>>()
    val historyResult: LiveData<List<Order>>
        get() = _history

    private val _historyDetail = MutableLiveData<List<DetailOrder>>()
    val historyResultDetail: LiveData<List<DetailOrder>>
        get() = _historyDetail

    private val _orderBooking = MutableLiveData<Order>()
    val orderBooking: LiveData<Order>
        get() = _orderBooking


    fun deleteCart(item: Order) = coroutineScope.launch {
        iOrderRepository.deleteCart()
    }

    fun deleteDetail(item: DetailOrder) = coroutineScope.launch {
        iOrderRepository.deleteDetail(item)
    }

    fun updateCart(item: Order) = coroutineScope.launch {
        iOrderRepository.updateCart(item)
    }

    fun updateDetail(item: DetailOrder) = coroutineScope.launch {
        iOrderRepository.updateDetail(item)
    }

    fun insertDetail(item: DetailOrder) = coroutineScope.launch {
        iOrderRepository.insertDetail(item)
    }

    fun getHistoryOrder() {
        coroutineScope.launch {
            val user = iUserRepository.getUser()
            val list = user.id?.let { iOrderRepository.getHistoryAPI(it) }
            _history.postValue(list!!)
        }
    }

    fun getCartDetail() {
        coroutineScope.launch {
            iOrderRepository.getDetailCart()
        }

    }

    fun booking(order: Order) {
        coroutineScope.launch {
            val orderB = iOrderRepository.booking(order)
            _orderBooking.postValue(orderB)
        }
    }

    fun getDetailOrder(id: Int) {
        coroutineScope.launch {
            val list = iOrderRepository.getOrderDetailsAPI(id)
            _historyDetail.postValue(list)
        }
    }


}