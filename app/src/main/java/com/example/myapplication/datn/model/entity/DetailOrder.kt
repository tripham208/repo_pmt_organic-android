package com.example.myapplication.datn.model.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "detail_order")
data class DetailOrder(
    @Json(name = "id")
    @ColumnInfo(name = "id") val id: Int?,
    @Json(name = "idhoadon")
    @ColumnInfo(name = "idOrder") val idhoadon: Int,
    @PrimaryKey
    @Json(name = "idsanpham")
    @ColumnInfo(name = "idProduct") val idsanpham: Int,
    @Json(name = "soluong")
    @ColumnInfo(name = "quantity") var soluong: Int,
    @Json(name = "dongia")
    @ColumnInfo(name = "unitPrice") val dongia: Int,
    @Json(name = "giamgia")
    @ColumnInfo(name = "discount") val giamgia: Int?,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeInt(idhoadon)
        parcel.writeInt(idsanpham)
        parcel.writeInt(soluong)
        parcel.writeInt(dongia)
        if (giamgia != null) {
            parcel.writeInt(giamgia)
        }
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