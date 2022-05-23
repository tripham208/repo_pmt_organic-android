package com.example.myapplication.datn.model.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    @Json(name = "id")
    @ColumnInfo(name = "id") val id: Int?,
    @Json(name = "username")
    @ColumnInfo(name = "username") var username: String,
    @Json(name = "password")
    @ColumnInfo(name = "password") var password: String,
    @Json(name = "fullName")
    @ColumnInfo(name = "name") var name: String,
    @Json(name = "phone")
    @ColumnInfo(name = "phone") var phone: Int,
    @Json(name = "typeAccount")
    @ColumnInfo(name = "type_account") var typeAccount: Int,
    @Json(name = "email")
    @ColumnInfo(name = "email") var email: String?,
    @Json(name = "address")
    @ColumnInfo(name = "address") var address: String?,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        if (id != null) {
            parcel.writeInt(id)
        }
        parcel.writeString(username)
        parcel.writeString(password)
        parcel.writeString(name)
        parcel.writeInt(phone)
        parcel.writeInt(typeAccount)
        parcel.writeString(email)
        parcel.writeString(address)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
