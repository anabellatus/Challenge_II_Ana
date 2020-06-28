package com.nanana.covidnow.viewmodel

import android.content.Context
import com.nanana.covidnow.data.HotlineModel
import com.nanana.covidnow.db.HotlineDatabase
import com.nanana.covidnow.repo.HotlineRepository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HotlineUpdateViewModel: ViewModel() {

    lateinit var repository: HotlineRepository

    fun init(context: Context) {
        val hotlineDao = HotlineDatabase.getDatabase(context).hotlineDao()
        repository = HotlineRepository(hotlineDao)
    }

    fun updateData(hotline: HotlineModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(hotline)
    }
}