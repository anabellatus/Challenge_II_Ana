package com.nanana.covidnow.repo

import androidx.lifecycle.LiveData
import com.nanana.covidnow.dao.HotlineDao
import com.nanana.covidnow.data.HotlineModel

class HotlineRepository(private val hotlineDao: HotlineDao) {

    val allHotline: LiveData<List<HotlineModel>> = hotlineDao.getAllhotline()

    suspend fun insert(hotline: HotlineModel) {
        hotlineDao.insert(hotline)
    }

    suspend fun insertAll(hotline: List<HotlineModel>) {
        hotlineDao.insertAll(hotline)
    }

    suspend fun deleteAll() {
        hotlineDao.deleteAll()
    }

    suspend fun update(hotline: HotlineModel) {
        hotlineDao.update(hotline)
    }

    suspend fun delete(hotline: HotlineModel) {
        hotlineDao.delete(hotline)
    }


}