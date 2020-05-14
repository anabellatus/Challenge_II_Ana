package com.nanana.covidnow.data

import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("indonesia")
    fun getIndo():Call<List<DataIndoItem>>
}