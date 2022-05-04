package com.example.myapplication.datn.model.entity

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class ProductType(
    @Json(name = "id")
    val id: Int,
    @Json(name = "ten")
    val name: String,
    @Json(name = "cha")
    val cha: Int?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeValue(cha)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductType> {
        override fun createFromParcel(parcel: Parcel): ProductType {
            return ProductType(parcel)
        }

        override fun newArray(size: Int): Array<ProductType?> {
            return arrayOfNulls(size)
        }
    }
}
