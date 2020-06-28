package com.nanana.covidnow.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanana.covidnow.data.HotlineModel
import com.nanana.covidnow.db.HotlineDatabase
import com.nanana.covidnow.repo.HotlineRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HotlineViewModel: ViewModel() {
    lateinit var repository: HotlineRepository

    fun init(context: Context) {
        val hotlineDao = HotlineDatabase.getDatabase(context).hotlineDao()
        repository = HotlineRepository(hotlineDao)
    }

    fun addData(hotline: HotlineModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(hotline)
    }
}