package com.example.myapplication.datn.database

import com.example.myapplication.datn.model.entity.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.internal.PrepareOp
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface AppAPI {

    @GET("product")
    suspend fun getProducts(): List<Product>

    @GET("bill/cart/{id}")
    suspend fun getCart(@Path("id") id: Int): Order

    @GET("bill/{id}")
    suspend fun getOrder(@Path("id") id: Int): Order

    @GET("user/checklogin/{username}/{password}")
    suspend fun getUser(
        @Path("username") username: String,
        @Path("password") password: String
    ): List<User>

    @GET("bill-detail/id-bill/{id}")
    suspend fun getDetails(@Path("id") id: Int): List<DetailOrder>

    @GET("bill/id-customer/{id}")
    suspend fun getOrdersByIdUser(@Path("id") id: Int): List<Order>

    @POST("bill-detail")
    suspend fun addDetail(@Body detailOrder: DetailOrder)

    @PUT("bill-detail/{id}")
    suspend fun updateDetail(@Path("id") id: Int, @Body detailOrder: DetailOrder)

    @DELETE("bill-detail/{id}")
    suspend fun deleteDetail(@Path("id") id: Int)

    @PUT("bill/{id}")
    suspend fun updateCart(@Path("id") id: Int, @Body order: Order)

    @PUT("bill/{id}")
    suspend fun booking(@Path("id") id: Int, @Body order: Order): Order

    @POST("user")
    suspend fun register(@Body user: User): List<User>

    @PUT("user/{id}")
    suspend fun updateUser(@Path("id") id: Int, @Body user: User)


    companion object {
        private const val CODE = "ddf0-2405-4803-fc5e-9610-8cd1-499b-64ce-c38d.ap.ngrok.io"
        const val BASE_URL = "https://$CODE/api/"
        const val IMG_URL = "https://$CODE"
    }
}

