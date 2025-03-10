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

    @Insert(onConflict = OnConflictStrategy.REPLACE) //skip if same content
    suspend fun insert(item: Product)

    @Query(
        "SELECT * FROM product " +
                "WHERE favorite = 1"
    )
    fun getFavorite(): Flow<List<Product>>

    @Update
    suspend fun update(vararg item: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE) //skip if same content
    suspend fun inserts(vararg item: Product)

    @Delete
    suspend fun delete(item: Product)

    @Query(
        "SELECT * FROM product " +
                "WHERE id = :id limit 1"
    )
    suspend fun getProduct(id: Int): Product

    @Query(
        "SELECT * FROM product " +
                "WHERE name LIKE '%' || :search || '%'"
    )
    suspend fun searchProduct(search: String): List<Product>

    @Query(
        "SELECT * FROM product " +
                "WHERE type = :search"
    )
    suspend fun searchProductType(search: Int): List<Product>

}