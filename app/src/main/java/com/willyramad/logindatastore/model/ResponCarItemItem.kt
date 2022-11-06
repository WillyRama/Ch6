package com.willyramad.logindatastore.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponCarItemItem(
    @SerializedName("category")
    val category: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("finish_rent_at")
    val finishRentAt: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("start_rent_at")
    val startRentAt: Any,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("updatedAt")
    val updatedAt: String
): Serializable