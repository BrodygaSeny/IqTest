package com.sentrin.iqtest.ui.endGame

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sentrin.iqtest.MyApplication.Companion.mainData
import com.sentrin.iqtest.data.MainEntity
import com.sentrin.iqtest.data.insertData
import com.sentrin.iqtest.scripts.getUserStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class EndGameViewModel (val context:Context, var sentData: MainEntity) : ViewModel() {
    var userStatus: String = getUserStatus(sentData.ScoreIq)

    var goToQuestAgain = MutableLiveData<Boolean>()
    var goToHome = MutableLiveData<Boolean>()

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun onAgain(){
        goToQuestAgain.value = true

        // clear data
        sentData.ScoreIq = mainData.startScore
        sentData.listAnsweredQuestions.clear()

        viewModelScope.launch {
            insertData(sentData)
        }
    }

    fun onHome(){
        goToHome.value = true
    }

}