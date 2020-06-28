package com.nanana.covidnow.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hotline")
data class HotlineModel (
    var nama: String,
    var nomor: String,
    @PrimaryKey var key: String
){
    constructor() : this("", "", "")
}