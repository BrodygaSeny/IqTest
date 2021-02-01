package com.sentrin.iqtest.ui.aboutApp

import android.R
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Environment
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sentrin.iqtest.MyApplication.Companion.mainData
import java.io.File
import java.io.FileOutputStream
import java.util.*


class AboutAppViewModel() : ViewModel(){
    var onShareClick = MutableLiveData<Boolean>()


    fun onShare() {
        onShareClick.value = true
    }

}