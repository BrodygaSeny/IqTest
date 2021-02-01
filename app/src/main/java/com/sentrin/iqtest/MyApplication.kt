package com.sentrin.iqtest

import android.app.Application
import com.sentrin.iqtest.data.MainData

class MyApplication: Application() {
    companion object {
        var mainData = MainData()
    }

    override fun onCreate() {

        super.onCreate()

        // initialization code here
    }
}