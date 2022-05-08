package com.example.myapplication.datn.model.dao.address

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.datn.model.entity.Order
import com.example.myapplication.datn.model.entity.Product
import com.example.myapplication.datn.model.entity.address.District
import com.example.myapplication.datn.model.entity.address.Ward

@Dao
interface AddressDao {
    @Query("SELECT * FROM district ")
    fun getDistrictToLiveData(): LiveData<List<District>>

    @Query("SELECT * FROM ward where district_code = :id")
    suspend fun getWardToLiveData(id: Int): List<Ward>

    @Insert(onConflict = OnConflictStrategy.IGNORE) //skip if same content
    suspend fun insertDistrict(item: District)

    @Insert(onConflict = OnConflictStrategy.IGNORE) //skip if same content
    suspend fun insertWard(item: Ward)
}