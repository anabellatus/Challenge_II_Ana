package com.nanana.covidnow.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gejala")
data class GejalaModel (
    var gejala: String,
    @PrimaryKey var key: String
){
    constructor() : this("", "")
}