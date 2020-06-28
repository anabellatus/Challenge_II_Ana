package com.nanana.covidnow.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nanana.covidnow.dao.HotlineDao
import com.nanana.covidnow.data.HotlineModel

@Database(entities = arrayOf(HotlineModel::class), version = 1, exportSchema = false)
public abstract class HotlineDatabase: RoomDatabase() {
    abstract fun hotlineDao(): HotlineDao

    companion object{
        @Volatile
        private var INSTANCE: HotlineDatabase? = null

        fun getDatabase(context: Context): HotlineDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null ){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HotlineDatabase::class.java,
                    "hotline_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}