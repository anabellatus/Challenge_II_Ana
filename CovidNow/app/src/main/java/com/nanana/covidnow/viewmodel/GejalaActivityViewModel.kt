package com.nanana.covidnow.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanana.covidnow.data.GejalaModel
import com.nanana.covidnow.db.GejalaDatabase
import com.nanana.covidnow.repo.GejalaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GejalaActivityViewModel : ViewModel() {
    lateinit var repository: GejalaRepository

    lateinit var allGejala: LiveData<List<GejalaModel>>

    fun init(context: Context) {
        val gejalaDao = GejalaDatabase.getDatabase(context).gejalaDao()
        repository = GejalaRepository(gejalaDao)
        allGejala = repository.allGejala
    }

    fun delete(gejala: GejalaModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(gejala)
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertAll(gejala: List<GejalaModel>) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
        repository.insertAll(gejala)
    }

}