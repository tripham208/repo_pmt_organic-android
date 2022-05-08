package com.example.myapplication.datn.repository.address

import androidx.lifecycle.LiveData
import com.example.myapplication.datn.model.entity.address.District
import com.example.myapplication.datn.model.entity.address.Ward

interface IAddressRepository {
    val district: LiveData<List<District>>
    suspend fun insertDistrict(item: District)
    suspend fun insertWard(item: Ward)
    suspend fun getData()
    suspend fun getWards(int: Int): List<Ward>
}