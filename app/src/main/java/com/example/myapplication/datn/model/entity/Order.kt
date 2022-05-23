package com.example.myapplication.datn.model.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "orderr")
data class Order(
    @PrimaryKey
    @Json(name = "id")
    @ColumnInfo(name = "id") val id: Int,
    @Json(name = "idCustomer")
    @ColumnInfo(name = "idUser") val idUser: Int,
    @Json(name = "idEmployee")
    @ColumnInfo(name = "idStaff") val idStaff: Int,
    @Json(name = "total")
    @ColumnInfo(name = "sum") val sum: Int,
    @Json(name = "time")
    @ColumnInfo(name = "time", defaultValue = "CURRENT_TIMESTAMP") val time: String?,
    @Json(name = "payment")
    @ColumnInfo(name = "pay") val payment: Int,
    @Json(name = "typeOrder")
    @ColumnInfo(name = "typeOrder") val typeOrder: Int,
    @Json(name = "note")
    @ColumnInfo(name = "note") val note: String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(idUser)
        parcel.writeInt(idStaff)
        parcel.writeInt(sum)
        parcel.writeString(time)
        parcel.writeInt(payment)
        parcel.writeInt(typeOrder)
        parcel.writeString(note)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Order> {
        override fun createFromParcel(parcel: Parcel): Order {
            return Order(parcel)
        }

        override fun newArray(size: Int): Array<Order?> {
            return arrayOfNulls(size)
        }
    }
}