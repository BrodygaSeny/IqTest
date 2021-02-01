package com.sentrin.iqtest.data

import android.os.Parcelable
import com.sentrin.iqtest.MyApplication.Companion.mainData
import kotlinx.android.parcel.Parcelize

@Parcelize
class Question(
    var questNumber:Int,
    var question:String,
    var rightAnswer:String,
    var otherAnswers:List<String>,
    var description:String,
    var descriptionTitle:String,
    var descriptionResource:String,
    var imgId:Int,
    var imgIdQuestion: Int

) : Parcelable

