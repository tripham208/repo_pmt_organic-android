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
    @Json(name = "name")
    @ColumnInfo(name = "name") val name: String?,
    @Json(name = "idTypeProduct")
    @ColumnInfo(name = "type") val idTypeProduct: Int,
    @Json(name = "image")
    @ColumnInfo(name = "image") val image: String?,
    @Json(name = "description")
    @ColumnInfo(name = "description") val description: String?,
    @Json(name = "unit")
    @ColumnInfo(name = "unit") val unit: String?,
    @Json(name = "quantity")
    @ColumnInfo(name = "quantity") val quantity: Int,
    @Json(name = "unitPrice")
    @ColumnInfo(name = "price") val price: Int,
    @Json(name = "idBranch")
    @ColumnInfo(name = "trademark") val idBranch: Int,
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
        parcel.writeString(name)
        parcel.writeInt(idTypeProduct)
        parcel.writeString(image)
        parcel.writeString(description)
        parcel.writeString(unit)
        parcel.writeInt(quantity)
        parcel.writeInt(price)
        parcel.writeInt(idBranch)
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