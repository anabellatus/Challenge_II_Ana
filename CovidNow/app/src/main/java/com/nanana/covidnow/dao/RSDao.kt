package com.nanana.covidnow.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nanana.covidnow.data.RSModel

@Dao
interface RSDao {
    @Query("SELECT * from rs")
    fun getAllRS(): LiveData<List<RSModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hospital: RSModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(hospitals: List<RSModel>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(hospital: RSModel)

    @Delete()
    suspend fun delete(hospital: RSModel)

    @Query("DELETE FROM rs")
    suspend fun deleteAll()
}