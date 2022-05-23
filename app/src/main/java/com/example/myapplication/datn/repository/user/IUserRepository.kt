package com.example.myapplication.datn.repository.user

import androidx.lifecycle.LiveData
import com.example.myapplication.datn.model.entity.User

interface IUserRepository {
    val user : LiveData<User>
    suspend fun checkLogin(username: String, pass: String): Boolean
    suspend fun checkPhone(int: Int): Boolean
    suspend fun getUser(): User
    suspend fun register(user: User):Boolean
    suspend fun updateUser(user: User)
    suspend fun logoutUser()
}