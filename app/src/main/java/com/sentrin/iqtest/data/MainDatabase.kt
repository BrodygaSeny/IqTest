package com.sentrin.iqtest.data

import android.content.Context
import androidx.room.*

@Database(entities = [MainEntity::class], version = 2)
@TypeConverters(IntListConverter::class)
abstract class MainDatabase: RoomDatabase() {
    abstract val mainEntityDao: MainEntityDao
}


private lateinit var INSTANCE: MainDatabase

fun getDatabase(context: Context): MainDatabase {
    synchronized(MainDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                MainDatabase ::class.java,
                "maindatabase")
                .build()
        }
    }
    return INSTANCE
}

