package com.sentrin.iqtest.data

import com.sentrin.iqtest.MyApplication.Companion.mainData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


suspend fun getData(): MainEntity?{
    return withContext(Dispatchers.IO) {
        mainData.database?.run {
            mainEntityDao!!.getAll().run {
                if (isEmpty()) null
                else first()
            }
        } ?: null
    }
}

suspend fun insertData(data: MainEntity){
    withContext(Dispatchers.IO) {
        mainData.database?.mainEntityDao!!.insertAll(listOf(data))
    }
}

suspend fun updateDarkTheme(bool:Boolean){
    withContext(Dispatchers.IO) {
        mainData.database?.mainEntityDao!!.updateDarkTheme(bool)
    }
}