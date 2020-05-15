package com.nanana.covidnow.data


import com.google.gson.annotations.SerializedName

data class DataProvinceItem(
    @SerializedName("attributes")
    val attributes: Attributes
)