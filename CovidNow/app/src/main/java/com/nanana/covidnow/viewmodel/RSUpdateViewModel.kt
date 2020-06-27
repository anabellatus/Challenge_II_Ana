package com.nanana.covidnow.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanana.covidnow.data.RSModel
import com.nanana.covidnow.db.RSDatabase
import com.nanana.covidnow.repo.RSRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RSUpdateViewModel: ViewModel() {

    lateinit var repository: RSRepository

    fun init(context: Context) {
        val rsDao = RSDatabase.getDatabase(context).rsDao()
        repository = RSRepository(rsDao)
    }

    fun updateData(rs: RSModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(rs)
    }

}