package com.example.myapplication.datn.database

import com.example.myapplication.datn.model.entity.Order
import com.example.myapplication.datn.model.entity.address.Province
import retrofit2.http.GET
import retrofit2.http.Path

interface AddressAPI {
    @GET("1?depth=3")
    suspend fun getData(): Province

    companion object {
        const val BASE_URL = "https://provinces.open-api.vn/api/p/"
    }
}