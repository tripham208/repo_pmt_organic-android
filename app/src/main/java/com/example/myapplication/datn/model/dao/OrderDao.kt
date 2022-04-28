package com.example.myapplication.datn.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.datn.model.entity.Order
import com.example.myapplication.datn.model.entity.Product
import com.example.myapplication.datn.model.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Query("SELECT * FROM orderr LIMIT 1 ")
    fun getOrderToLiveData(): LiveData<Order>

    @Insert(onConflict = OnConflictStrategy.IGNORE) //skip if same content
    suspend fun insert(item: Order)

    @Delete
    suspend fun delete(item: Order)

    @Update
    suspend fun updates(vararg item: Order)



}