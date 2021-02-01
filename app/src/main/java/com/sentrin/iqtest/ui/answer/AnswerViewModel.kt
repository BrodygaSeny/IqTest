package com.sentrin.iqtest.ui.answer

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sentrin.iqtest.MyApplication.Companion.mainData
import com.sentrin.iqtest.data.MainEntity
import com.sentrin.iqtest.data.Question
import com.sentrin.iqtest.data.getData
import com.sentrin.iqtest.data.insertData
import com.sentrin.iqtest.scripts.getStringByName
import com.sentrin.iqtest.scripts.getUserStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.random.Random

class AnswerViewModel(
    val context: Context,
    val question: Question,
    private val isAnswerRight:Boolean,
    var sentData:MainEntity
) : ViewModel() {

    var onBackBool = MutableLiveData<Boolean>()
    var onNextBool = MutableLiveData<Boolean>()
    lateinit var userStatus : String
    lateinit var answerTitleText:String

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        if (isAnswerRight){
            sentData.ScoreIq += mainData.plusScore
        }
        else {
            sentData.ScoreIq -= mainData.minusScore
        }
        sentData.listAnsweredQuestions.add(question.questNumber)

        userStatus = getUserStatus(sentData.ScoreIq)
        answerTitleText = getTitle()

        saveInDb(sentData)
    }

    fun saveInDb(data:MainEntity){
        viewModelScope.launch {
            insertData(data)
        }
    }

    fun getTitle():String{
        var highRandom = 0; var beginString = ""
        if (isAnswerRight) {
            highRandom =  mainData.rightAnswerTitleNumber
            beginString = "answerRight"
        }
        else {
            highRandom =  mainData.falseAnswerTitleNumber
            beginString = "answerFalse"
        }
        val randomTitle = Random.nextInt(1, highRandom+1)
        return getStringByName("$beginString$randomTitle")
    }



    fun onBack(){
        onBackBool.value = true
    }

    fun onNext(){
        onNextBool.value = true
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
