package com.sentrin.iqtest.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MainEntityDao {

    @Query("select * from mainentity")
    fun getAll(): List<MainEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( videos: List<MainEntity>)

    @Query("update mainentity set darkTheme = :value where id = 0")
    fun updateDarkTheme(value:Boolean)

}