package com.nanana.covidnow.data


import com.google.gson.annotations.SerializedName

data class DataIndoItem(
    @SerializedName("dirawat")
    val dirawat: String,
    @SerializedName("meninggal")
    val meninggal: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("positif")
    val positif: String,
    @SerializedName("sembuh")
    val sembuh: String
)