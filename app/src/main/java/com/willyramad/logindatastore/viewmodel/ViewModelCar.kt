package com.willyramad.logindatastore.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.willyramad.logindatastore.model.ResponCarItemItem
import com.willyramad.logindatastore.service.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ViewModelCar @Inject constructor(var api : ApiService) : ViewModel(){
    lateinit var liveDataCar : MutableLiveData<List<ResponCarItemItem>>
    lateinit var detailCar: MutableLiveData<ResponCarItemItem>

    init {
        liveDataCar = MutableLiveData()
        detailCar = MutableLiveData()
    }
    fun getAllFilm() : MutableLiveData<List<ResponCarItemItem>> {
        return liveDataCar
    }
    fun getDetail(id : Int) : MutableLiveData<ResponCarItemItem> {
        return  detailCar
    }

    fun callAllFillm(){
        api.getAllcar().enqueue(object : Callback<List<ResponCarItemItem>>{
                override fun onResponse(
                    call: Call<List<ResponCarItemItem>>,
                    response: Response<List<ResponCarItemItem>>
                ) {
                    if (response.isSuccessful){
                        liveDataCar.postValue(response.body())
                    }else{
                        liveDataCar.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<ResponCarItemItem>>, t: Throwable) {
                    liveDataCar.postValue(null)
                }

            })
    }
    fun callDetail(id: Int){
        api.getDetail(id).enqueue(object :Callback<ResponCarItemItem>{
            override fun onResponse(
                call: Call<ResponCarItemItem>,
                response: Response<ResponCarItemItem>
            ) {
                if (response.isSuccessful){
                    detailCar.postValue(response.body())
                }else{
                    detailCar.postValue(null)
                }
            }

            override fun onFailure(call: Call<ResponCarItemItem>, t: Throwable) {
                detailCar.postValue(null)
            }

        })
    }
}