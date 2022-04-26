package com.example.myapplication.datn.model.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detail_order")
class DetailOrder(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "idOrder") val idhoadon: Int,
    @ColumnInfo(name = "idProduct") val idsanpham: Int,
    @ColumnInfo(name = "quantity") val soluong: String?,
    @ColumnInfo(name = "unitPrice") val dongia: String?,
    @ColumnInfo(name = "discount") val giamgia: String?,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeInt(idhoadon)
        parcel.writeInt(idsanpham)
        parcel.writeString(soluong)
        parcel.writeString(dongia)
        parcel.writeString(giamgia)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DetailOrder> {
        override fun createFromParcel(parcel: Parcel): DetailOrder {
            return DetailOrder(parcel)
        }

        override fun newArray(size: Int): Array<DetailOrder?> {
            return arrayOfNulls(size)
        }
    }
}