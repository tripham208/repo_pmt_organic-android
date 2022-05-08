package com.example.myapplication.datn.model.dto

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.myapplication.datn.model.entity.address.District
import com.example.myapplication.datn.model.entity.address.Ward
import com.squareup.moshi.Json

data class DistrictDto(
    @Json(name = "name")
    val name: String,
    @Json(name = "code") val code: Int,
    @Json(name = "province_code")
    val provinceCode: Int,
    @Json(name = "wards")
    val wards: List<Ward>,
) {
    fun toDistrict(): District {
        return District(name, code, provinceCode)
    }
}
