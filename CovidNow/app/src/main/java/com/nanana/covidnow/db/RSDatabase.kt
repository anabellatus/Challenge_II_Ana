package com.nanana.covidnow.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nanana.covidnow.dao.RSDao
import com.nanana.covidnow.data.RSModel

@Database(entities = arrayOf(RSModel::class), version = 1, exportSchema = false)
public abstract class RSDatabase: RoomDatabase() {
    abstract fun rsDao(): RSDao

    companion object{
        @Volatile
        private var INSTANCE: RSDatabase? = null

        fun getDatabase(context: Context): RSDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null ){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RSDatabase::class.java,
                    "rs_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}