package com.example.myapplication.datn.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.datn.model.entity.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {


    @Query("SELECT * FROM product ")
    fun getAllToFlow(): Flow<List<Product>>

    @Query("SELECT * FROM product ")
    fun getAllToLiveData(): LiveData<List<Product>>

    @Insert(onConflict = OnConflictStrategy.IGNORE) //skip if same content
    suspend fun insert(item: Product)

    @Query(
        "SELECT * FROM product " +
                "WHERE favorite = 1"
    )
    fun getFavorite(): Flow<List<Product>>

    @Update
    suspend fun update(vararg item: Product)


    @Delete
    suspend fun delete(item: Product)

}