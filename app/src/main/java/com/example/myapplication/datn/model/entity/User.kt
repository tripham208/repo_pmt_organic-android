package com.example.myapplication.datn.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    @Json(name = "id")
    @ColumnInfo(name = "id") val id: Int,
    @Json(name = "taikhoan")
    @ColumnInfo(name = "username") var username: String,
    @Json(name = "password")
    @ColumnInfo(name = "password") var password: String,
    @Json(name = "ten")
    @ColumnInfo(name = "name") var name: String,
    @Json(name = "sdt")
    @ColumnInfo(name = "phone") var phone: Int,
    @Json(name = "loaitaikhoan")
    @ColumnInfo(name = "type_account") var loaitaikhoan: Int,
    @Json(name = "email")
    @ColumnInfo(name = "email") var email: String,
    @Json(name = "diachi")
    @ColumnInfo(name = "address") var address: String,
)
