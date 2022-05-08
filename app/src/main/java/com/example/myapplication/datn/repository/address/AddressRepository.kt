package com.example.myapplication.datn.repository.address

import androidx.lifecycle.LiveData
import com.example.myapplication.datn.database.AddressAPI
import com.example.myapplication.datn.model.dao.address.AddressDao
import com.example.myapplication.datn.model.entity.address.District
import com.example.myapplication.datn.model.entity.address.Ward
import com.example.myapplication.datn.utils.Logger
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddressRepository @Inject constructor(
    private val addressDao: AddressDao,
    private val api: AddressAPI
) : IAddressRepository {
    override val district: LiveData<List<District>> = addressDao.getDistrictToLiveData()

    override suspend fun insertDistrict(item: District) {
        addressDao.insertDistrict(item)
    }

    override suspend fun insertWard(item: Ward) {
        addressDao.insertWard(item)
    }

    override suspend fun getData() {
        val province = api.getData()
        province.district.forEach { dis->
            insertDistrict(dis.toDistrict())
            dis.wards.forEach { ward ->
                insertWard(ward)
            }
        }
    }

    override suspend fun getWards(int: Int): List<Ward> {
        return addressDao.getWardToLiveData(int)
    }
}