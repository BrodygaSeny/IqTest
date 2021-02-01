package com.sentrin.iqtest.ui.question

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sentrin.iqtest.MyApplication.Companion.mainData
import com.sentrin.iqtest.R
import com.sentrin.iqtest.data.MainEntity
import com.sentrin.iqtest.data.Question
import com.sentrin.iqtest.data.getData
import com.sentrin.iqtest.data.insertData
import com.sentrin.iqtest.scripts.getQuestion
import kotlinx.coroutines.*
import kotlin.random.Random

class QuestionViewModel(val context:Context, var sentData:MainEntity) : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    var navigateOnAnswer = MutableLiveData<Boolean>()
    var navigateOnEndGame = MutableLiveData<Boolean>()

    var question : Question?


    init{
        question = nextQuestion(sentData)
        if (question == null) {
            navigateOnEndGame.value = true
        }
    }


    fun nextQuestion(data:MainEntity):Question?{
        var allQuestList = listOf<Int>();
        var remainingQuestList = listOf<Int>();

        for ((typeQuestions,rangeQuestions) in mainData.questionRange.withIndex()){
            allQuestList = rangeQuestions.toList()
            remainingQuestList = allQuestList.filter { it !in data.listAnsweredQuestions }
            if (remainingQuestList.isNotEmpty()) break
            else continue
        }

        if (remainingQuestList.isEmpty()) return null


//        allQuestList = (1 .. mainData.starterNumberQuestion).toList()
//        remainingQuestList = allQuestList.filter { it !in data.listAnsweredQuestions }
//        if (remainingQuestList.isEmpty()) return null

        val questNumber = remainingQuestList.random()

        return getQuestion(questNumber)
    }


    fun onAnswer(){
        navigateOnAnswer.value = true
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}