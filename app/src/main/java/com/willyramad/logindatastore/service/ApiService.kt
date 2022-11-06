package com.willyramad.logindatastore.service

import com.willyramad.logindatastore.model.ResponCarItemItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("admin/car")
    fun getAllcar() : Call<List<ResponCarItemItem>>

    @GET("admin/car/{id}")
    fun getDetail(@Path("id") id : Int): Call<ResponCarItemItem>
}