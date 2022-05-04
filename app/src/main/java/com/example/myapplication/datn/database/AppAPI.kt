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

    @POST("bill")
    suspend fun updateCart(@Body order: Order)

    @POST("user")
    suspend fun register(@Body user: User): List<User>


    /*
    @GET("category/update/{id}")
    suspend fun updateCart(@Body order: ProductType,("id") id: Int)
*/
    companion object {
        private const val CODE = "debf-2405-4803-fc5d-d720-bdc9-366c-281d-7a41.ap.ngrok.io"
        const val BASE_URL = "https://$CODE/api/"
        const val IMG_URL = "https://$CODE"
    }
}

