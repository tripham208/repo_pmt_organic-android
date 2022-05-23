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
    @Json(name = "idOrder")
    @ColumnInfo(name = "idOrder") val idOrder: Int,
    @PrimaryKey
    @Json(name = "idProduct")
    @ColumnInfo(name = "idProduct") val idProduct: Int,
    @Json(name = "quantity")
    @ColumnInfo(name = "quantity") var quantity: Int,
    @Json(name = "unitPrice")
    @ColumnInfo(name = "unitPrice") val unitPrice: Int,
    @Json(name = "discount")
    @ColumnInfo(name = "discount") val discount: Int?,
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
        parcel.writeInt(idOrder)
        parcel.writeInt(idProduct)
        parcel.writeInt(quantity)
        parcel.writeInt(unitPrice)
        if (discount != null) {
            parcel.writeInt(discount)
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