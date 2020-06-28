package com.nanana.covidnow.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanana.covidnow.data.HotlineModel
import com.nanana.covidnow.db.HotlineDatabase
import com.nanana.covidnow.repo.HotlineRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HotlineActivityViewModel: ViewModel() {
    lateinit var repository: HotlineRepository

    lateinit var allHotline: LiveData<List<HotlineModel>>

    fun init(context: Context) {
        val hotlineDao = HotlineDatabase.getDatabase(context).hotlineDao()
        repository = HotlineRepository(hotlineDao)
        allHotline = repository.allHotline
    }

    fun delete(hotline: HotlineModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(hotline)
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertAll(hotline: List<HotlineModel>) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
        repository.insertAll(hotline)
    }

}