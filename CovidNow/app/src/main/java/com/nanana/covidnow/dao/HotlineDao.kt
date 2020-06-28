package com.nanana.covidnow.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nanana.covidnow.data.HotlineModel

@Dao
interface HotlineDao {

    @Query("SELECT * from hotline")
    fun getAllhotline(): LiveData<List<HotlineModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hotline: HotlineModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(hotline: List<HotlineModel>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(hotline: HotlineModel)

    @Delete()
    suspend fun delete(hotline: HotlineModel)

    @Query("DELETE FROM hotline")
    suspend fun deleteAll()
}