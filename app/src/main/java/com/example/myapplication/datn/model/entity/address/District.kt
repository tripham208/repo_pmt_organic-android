package com.example.myapplication.datn.model.entity.address

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "district" )
data class District(
    @Json(name = "name")
    @ColumnInfo(name = "name") val name: String,
    @PrimaryKey
    @Json(name = "code")
    @ColumnInfo(name = "code") val code: Int,
    @Json(name = "province_code")
    @ColumnInfo(name = "province_code") val provinceCode: Int,
){

}