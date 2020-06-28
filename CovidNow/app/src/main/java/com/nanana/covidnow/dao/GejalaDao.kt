package com.nanana.covidnow.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nanana.covidnow.data.GejalaModel

@Dao
interface GejalaDao {

    @Query("SELECT * from gejala")
    fun getAllGejala(): LiveData<List<GejalaModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gejala: GejalaModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(gejala: List<GejalaModel>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(gejala: GejalaModel)

    @Delete()
    suspend fun delete(gejala: GejalaModel)

    @Query("DELETE FROM rs")
    suspend fun deleteAll()
}