package com.example.myapplication.datn.model.entity.address

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
@Entity(tableName = "Ward")
data class Ward(
    @Json(name = "name")
    @ColumnInfo(name = "name") val name: String,
    @PrimaryKey
    @Json(name = "code")
    @ColumnInfo(name = "code") val code: Int,
    @Json(name = "district_code")
    @ColumnInfo(name = "district_code") val district_code: Int
)