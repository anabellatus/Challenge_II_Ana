package com.nanana.covidnow.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rs")
data class RSModel(
    var nama: String,
    var alamat: String,
    var telp: String,
    @PrimaryKey var key: String
){
    constructor() : this("", "", "", "" )
}