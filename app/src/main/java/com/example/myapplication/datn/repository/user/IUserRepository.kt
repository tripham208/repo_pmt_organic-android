package com.example.myapplication.datn.repository.user

import com.example.myapplication.datn.model.entity.User

interface IUserRepository {
    fun getUser(): User
}