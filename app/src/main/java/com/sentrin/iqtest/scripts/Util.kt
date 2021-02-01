package com.sentrin.iqtest.scripts

import android.content.Context
import android.content.res.Resources
import android.util.Log
import com.sentrin.iqtest.MyApplication.Companion.mainData
import com.sentrin.iqtest.R
import com.sentrin.iqtest.data.MainEntity
import com.sentrin.iqtest.data.Question
import com.sentrin.iqtest.data.userStatusList


fun getQuestion(questNumber: Int):Question
{
    val context = mainData.contextMainActivity!!

    val question = getStringByName("question_$questNumber")
    val rightAnswer = getStringByName("answer_${questNumber}_0")
    val outherAnswers = listOf<String>(
        getStringByName("answer_${questNumber}_1"),
        getStringByName("answer_${questNumber}_2"),
        getStringByName("answer_${questNumber}_3")
    )
    val description = getStringByName("description_$questNumber")
    val descriptionTitle = getStringByName("description_title_$questNumber")
    val descriptionRecourse = getStringByName("description_resource_$questNumber")
    val imgId = context!!.resources.getIdentifier(
        "draw_question_${questNumber}",
        "drawable",
        context.getPackageName()
    )
    val imgIdQuestion  = context!!.resources.getIdentifier(
        "question_${questNumber}",
        "drawable",
        context.getPackageName()
    )

    Log.d("debug1", "img id = $imgId")

    return Question(
        questNumber,
        question,
        rightAnswer,
        outherAnswers,
        description,
        descriptionTitle,
        descriptionRecourse,
        imgId,
        imgIdQuestion
    )
}

// Util for get property by name
fun getStringByName(stringName: String): String {
    val res: Resources = mainData.contextMainActivity!!.resources
    try {
        return res.getString(res.getIdentifier(stringName, "string", mainData.contextMainActivity!!.getPackageName()))
    }
    catch (e:Resources.NotFoundException){
        return res.getString(R.string.exceptionStringNotFound)
    }
}


fun getUserStatus(scoreIq:Int):String{
    val rows = userStatusList.keys
    for(row in rows){
        if (scoreIq in row)
            return userStatusList[row]!!
    }
    return mainData.contextMainActivity!!.resources.getString(R.string.errorNotFoundStatus)
}







