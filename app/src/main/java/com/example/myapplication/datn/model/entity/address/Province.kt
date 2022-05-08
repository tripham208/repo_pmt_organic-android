package com.example.myapplication.datn.model.entity.address

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.myapplication.datn.model.dto.DistrictDto
import com.squareup.moshi.Json


data class Province(
    @Json(name = "name")
    val name: String,
    @Json(name = "code")
    val code: String,
    @Json(name = "districts")
    val district: List<DistrictDto>
)
