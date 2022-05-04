package com.example.myapplication.datn.repository.user

import androidx.lifecycle.LiveData
import com.example.myapplication.datn.database.AppAPI
import com.example.myapplication.datn.model.dao.DetailOrderDao
import com.example.myapplication.datn.model.dao.OrderDao
import com.example.myapplication.datn.model.dao.ProductDao
import com.example.myapplication.datn.model.dao.UserDao
import com.example.myapplication.datn.model.entity.DetailOrder
import com.example.myapplication.datn.model.entity.User
import com.example.myapplication.datn.repository.user.IUserRepository
import com.example.myapplication.datn.utils.Logger
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val orderDao: OrderDao,
    private val api: AppAPI,
    private val detailOrderDao: DetailOrderDao
) : IUserRepository {
    override val user: LiveData<User> = userDao.getUserToLiveData()
    override suspend fun checkLogin(username: String, pass: String): Boolean {
        val x = api.getUser(username, pass)
        if (x.size == 1) {
            userDao.insert(x[0])
            x[0].id?.let { api.getCart(it) }?.let { it ->
                orderDao.insert(it)
                api.getDetails(it.id).forEach {
                    detailOrderDao.insert(it)
                }
            }

            return true
        }
        return false
    }

    override suspend fun getUser(): User {
        return userDao.getUser()
    }

    override suspend fun register(user: User): Boolean {
        val rs = api.register(user)
        if (rs.isEmpty())
            return false
        else {
            userDao.insert(rs[0])
            rs[0].id?.let { api.getCart(it) }?.let { orderDao.insert(it) }
        }
        return true
    }
}