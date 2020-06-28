package com.nanana.covidnow.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nanana.covidnow.dao.GejalaDao
import com.nanana.covidnow.data.GejalaModel


@Database(entities = arrayOf(GejalaModel::class), version = 1, exportSchema = false)
public abstract class GejalaDatabase : RoomDatabase() {
    abstract fun gejalaDao(): GejalaDao

    companion object{
        @Volatile
        private var INSTANCE: GejalaDatabase? = null

        fun getDatabase(context: Context): GejalaDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null ){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GejalaDatabase::class.java,
                    "gejala_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}