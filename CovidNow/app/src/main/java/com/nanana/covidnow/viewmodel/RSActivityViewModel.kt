package com.nanana.covidnow.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanana.covidnow.data.RSModel
import com.nanana.covidnow.db.RSDatabase
import com.nanana.covidnow.repo.RSRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RSActivityViewModel: ViewModel() {
    lateinit var repository: RSRepository

    lateinit var allRS: LiveData<List<RSModel>>

    fun init(context: Context) {
        val rsDao = RSDatabase.getDatabase(context).rsDao()
        repository = RSRepository(rsDao)
        allRS = repository.allRS
    }

    fun delete(rs: RSModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(rs)
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertAll(hospitals: List<RSModel>) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
        repository.insertAll(hospitals)
    }

}