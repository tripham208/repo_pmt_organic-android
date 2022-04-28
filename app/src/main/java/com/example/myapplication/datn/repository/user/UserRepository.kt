package com.example.myapplication.datn.repository.user

import androidx.lifecycle.LiveData
import com.example.myapplication.datn.database.AppAPI
import com.example.myapplication.datn.model.dao.OrderDao
import com.example.myapplication.datn.model.dao.ProductDao
import com.example.myapplication.datn.model.dao.UserDao
import com.example.myapplication.datn.model.entity.User
import com.example.myapplication.datn.repository.user.IUserRepository
import com.example.myapplication.datn.utils.Logger
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val orderDao: OrderDao,
    private val api: AppAPI
) : IUserRepository {
    override val user: LiveData<User> = userDao.getUserToLiveData()
    override suspend fun checkLogin(username: String, pass: String): Boolean {
        val x = api.getUser(username,pass)
        if (x.size==1){
            userDao.insert(x[0])
            orderDao.insert(api.getCart(x[0].id))
            return true
        }
        return false
    }

    override suspend fun getUser(): User {
        return userDao.getUser()
    }
}