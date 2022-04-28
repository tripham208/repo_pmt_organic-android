package com.example.myapplication.datn.database

import com.example.myapplication.datn.model.entity.DetailOrder
import com.example.myapplication.datn.model.entity.Order
import com.example.myapplication.datn.model.entity.Product
import com.example.myapplication.datn.model.entity.User
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface AppAPI {

    @GET("product")
    suspend fun getProducts(): List<Product>

    @GET("bill/cart/{id}")
    suspend fun getCart(@Path("id") id: Int): Order

    @GET("user/checklogin/{username}/{password}")
    suspend fun getUser(
        @Path("username") username: String,
        @Path("password") password: String
    ): List<User>

    @GET("bill-detail/id-bill/{id}")
    suspend fun getDetails(@Path("id") id: Int): List<DetailOrder>

    @GET("bill/id-customer/{id}")
    suspend fun getOrdersByIdUser(@Path("id") id: Int): List<Order>

    companion object {
        private const val CODE = "f33b-2405-4803-fc5d-d720-24a0-e905-41ec-be1a.ap.ngrok.io"
        const val BASE_URL = "https://$CODE/api/"
    }
}

