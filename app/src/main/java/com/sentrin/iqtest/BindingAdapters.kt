package com.sentrin.iqtest

import android.graphics.drawable.ColorDrawable
import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.sentrin.iqtest.MyApplication.Companion.mainData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

@BindingAdapter("imgId")
fun setOnImgId(view:ImageView, imgId:Int){
    view.setImageResource(imgId)
}

@BindingAdapter("justify")
fun setJustify(view: TextView, bool:Boolean){
    if (bool){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            view.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        }
    }
}




