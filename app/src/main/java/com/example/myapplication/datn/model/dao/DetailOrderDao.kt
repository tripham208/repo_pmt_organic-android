package com.example.myapplication.datn.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.datn.model.entity.DetailOrder
import com.example.myapplication.datn.model.entity.Order
import com.example.myapplication.datn.model.entity.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface DetailOrderDao {
    @Query("SELECT * FROM detail_order ")
    fun getAllToFlow(): Flow<List<DetailOrder>>

    @Query("SELECT * FROM detail_order ")
    fun getAllToLiveData(): LiveData<List<DetailOrder>>

    @Insert(onConflict = OnConflictStrategy.IGNORE) //skip if same content
    suspend fun insert(item: DetailOrder)

    @Delete
    suspend fun delete(item: DetailOrder)

    @Update
    suspend fun updates(vararg item: DetailOrder)

    @Query(
        "SELECT * FROM detail_order " +
                "WHERE idProduct = :intt "
    )
    suspend fun getItem(intt: Int): DetailOrder

    @Query("DELETE FROM detail_order")
    suspend fun deleteAll()

}