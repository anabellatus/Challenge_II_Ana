package com.nanana.covidnow.data


import com.google.gson.annotations.SerializedName

data class DataKasus(
    @SerializedName("data")
    val `data`: List<Data>
)