package com.nanana.covidnow.repo

import androidx.lifecycle.LiveData
import com.nanana.covidnow.dao.GejalaDao
import com.nanana.covidnow.data.GejalaModel

class GejalaRepository(private val gejalaDao: GejalaDao) {

    val allGejala: LiveData<List<GejalaModel>> = gejalaDao.getAllGejala()

    suspend fun insert(gejala: GejalaModel) {
        gejalaDao.insert(gejala)
    }

    suspend fun insertAll(gejalaa: List<GejalaModel>) {
        gejalaDao.insertAll(gejalaa)
    }

    suspend fun deleteAll() {
        gejalaDao.deleteAll()
    }

    suspend fun update(gejala: GejalaModel) {
        gejalaDao.update(gejala)
    }

    suspend fun delete(gejala: GejalaModel) {
        gejalaDao.delete(gejala)
    }


}