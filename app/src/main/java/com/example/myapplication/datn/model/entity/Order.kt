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
    @Json(name = "idkhachhang")
    @ColumnInfo(name = "idUser") val idkhachhang: Int,
    @Json(name = "idnhanvien")
    @ColumnInfo(name = "idStaff") val idnhanvien: Int,
    @Json(name = "tongtien")
    @ColumnInfo(name = "sum") val tongtien: Int,
    @Json(name = "thoigian")
    @ColumnInfo(name = "time", defaultValue = "CURRENT_TIMESTAMP") val thoigian: String?,
    @Json(name = "thanhtoan")
    @ColumnInfo(name = "pay") val thanhtoan: Int,
    @Json(name = "loaidon")
    @ColumnInfo(name = "typeOrder") val loaidon: Int,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeInt(idkhachhang)
        parcel.writeInt(idnhanvien)
        parcel.writeInt(tongtien)
        parcel.writeString(thoigian)
        parcel.writeInt(thanhtoan)
        parcel.writeInt(loaidon)
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

