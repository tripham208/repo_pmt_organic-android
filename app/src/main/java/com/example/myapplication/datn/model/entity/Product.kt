package com.example.myapplication.datn.model.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "product")
data class Product(
    @PrimaryKey
    @Json(name = "id")
    @ColumnInfo(name = "id") val id: Int,
    @Json(name = "ten")
    @ColumnInfo(name = "name") val ten: String?,
    @Json(name = "idloai")
    @ColumnInfo(name = "type") val idloai: Int,
    @Json(name = "anh")
    @ColumnInfo(name = "image") val anh: String?,
    @Json(name = "mota")
    @ColumnInfo(name = "description") val mota: String?,
    @Json(name = "donvi")
    @ColumnInfo(name = "unit") val donvi: String?,
    @Json(name = "soluong")
    @ColumnInfo(name = "quantity") val soluong: Int,
    @Json(name = "dongia")
    @ColumnInfo(name = "price") val dongia: Int,
    @Json(name = "idthuonghieu")
    @ColumnInfo(name = "trademark") val idthuonghieu: Int,
    @ColumnInfo(name = "favorite") val favorite: Boolean = false,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(ten)
        parcel.writeInt(idloai)
        parcel.writeString(anh)
        parcel.writeString(mota)
        parcel.writeString(donvi)
        parcel.writeInt(soluong)
        parcel.writeInt(dongia)
        parcel.writeInt(idthuonghieu)
        parcel.writeByte(if (favorite) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}