package com.willyramad.logindatastore.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


@Entity
@Parcelize
data class FavoriteCar(
    @PrimaryKey
    val id : Int?,
    @ColumnInfo(name = "image")
    var image : String,
    @ColumnInfo(name = "name")
    var nama : String,
    @ColumnInfo(name = "category")
    var category : String,
    @ColumnInfo(name = "price")
    var price : String
):Parcelable
