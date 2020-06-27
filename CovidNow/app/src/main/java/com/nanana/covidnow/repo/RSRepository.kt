package com.nanana.covidnow.repo

import androidx.lifecycle.LiveData
import com.nanana.covidnow.dao.RSDao
import com.nanana.covidnow.data.RSModel

class RSRepository(private val rsDao: RSDao) {

    val allRS: LiveData<List<RSModel>> = rsDao.getAllRS()

    suspend fun insert(rs: RSModel) {
        rsDao.insert(rs)
    }

    suspend fun insertAll(hospitals: List<RSModel>) {
        rsDao.insertAll(hospitals)
    }

    suspend fun deleteAll() {
        rsDao.deleteAll()
    }

    suspend fun update(rs: RSModel) {
        rsDao.update(rs)
    }

    suspend fun delete(rs: RSModel) {
        rsDao.delete(rs)
    }


}