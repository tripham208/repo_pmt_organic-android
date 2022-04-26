package com.example.myapplication.datn.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.datn.model.entity.Product
import com.example.myapplication.datn.model.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) //skip if same content
    suspend fun insert(item: User)

    @Query("SELECT * FROM user LIMIT 1 ")
    fun getUserToLiveData(): LiveData<User>
}