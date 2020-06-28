package com.nanana.covidnow.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanana.covidnow.data.GejalaModel
import com.nanana.covidnow.db.GejalaDatabase
import com.nanana.covidnow.repo.GejalaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GejalaViewModel: ViewModel() {
    lateinit var repository: GejalaRepository

    fun init(context: Context) {
        val gejalaDao = GejalaDatabase.getDatabase(context).gejalaDao()
        repository = GejalaRepository(gejalaDao)
    }

    fun addData(gejala: GejalaModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(gejala)
    }
}