package com.example.myapplication.datn.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.datn.model.entity.Order
import com.example.myapplication.datn.model.entity.Product
import com.example.myapplication.datn.model.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) //skip if same content
    suspend fun insert(item: User)

    @Query("SELECT * FROM user LIMIT 1 ")
    fun getUserToLiveData(): LiveData<User>

    @Query("SELECT * FROM user LIMIT 1 ")
    suspend fun getUser(): User

    @Query("DELETE FROM user")
    suspend fun deleteAll()

    @Update
    suspend fun updates(vararg item: User)
}