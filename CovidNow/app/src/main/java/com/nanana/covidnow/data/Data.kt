package com.nanana.covidnow.data


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("added_date")
    val addedDate: String,
    @SerializedName("detail_wn")
    val detailWn: Any,
    @SerializedName("garis_penularan")
    val garisPenularan: Any,
    @SerializedName("id_cluster")
    val idCluster: Any,
    @SerializedName("id_kasus")
    val idKasus: Any,
    @SerializedName("id_lab")
    val idLab: Int,
    @SerializedName("id_pasien")
    val idPasien: Int,
    @SerializedName("id_status")
    val idStatus: Int,
    @SerializedName("jenis_kelamin")
    val jenisKelamin: Int,
    @SerializedName("keterangan")
    val keterangan: Any,
    @SerializedName("keterangan_status")
    val keteranganStatus: Any,
    @SerializedName("kode_pasien")
    val kodePasien: Int,
    @SerializedName("lat")
    val lat: Any,
    @SerializedName("long")
    val long: Any,
    @SerializedName("provinsi")
    val provinsi: Int,
    @SerializedName("tampilkan")
    val tampilkan: Int,
    @SerializedName("umur")
    val umur: Int,
    @SerializedName("wn")
    val wn: Int
)