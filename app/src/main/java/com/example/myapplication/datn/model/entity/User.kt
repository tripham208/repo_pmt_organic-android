package com.example.myapplication.datn.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int?,
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "phone") var phone: Int,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "address") var address: String,
)

