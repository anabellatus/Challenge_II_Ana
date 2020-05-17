package com.nanana.covidnow.data

import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("indonesia")
    fun getIndo():Call<List<DataIndoItem>>

    @GET("indonesia/provinsi")
    fun getProvince():Call<List<DataProvinceItem>>

    @GET("kasus")
    fun getKasus():Call<List<Data>>
}