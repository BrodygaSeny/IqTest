package com.sentrin.iqtest.ui.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sentrin.iqtest.data.MainEntity
import com.sentrin.iqtest.data.getData
import com.sentrin.iqtest.data.getDatabase
import com.sentrin.iqtest.scripts.getUserStatus
import kotlinx.coroutines.*

class HomeViewModel(val context: Context, val sentData:MainEntity?) : ViewModel() {

    var data = MutableLiveData<MainEntity?>()
    var goToQuest = MutableLiveData<Boolean>()
    var userStatus = MutableLiveData<String>()
    var darkTheme = MutableLiveData<Boolean>()

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main )



    init{
        //if start app sent data == null and we must initialize data from database
        if (sentData!= null) {
            data.value = sentData
            userStatus.value = getUserStatus(data.value!!.ScoreIq)
        }
        else initDatabase()

        viewModelScope.launch {
            darkTheme.value = getData()?.darkTheme ?: false
        }
    }

    fun onStart(){
        goToQuest.value = true
    }


    private fun initDatabase(){
        viewModelScope.launch {
            data.value = getData()
            if (data.value != null)
                userStatus.value = getUserStatus(data.value!!.ScoreIq)
        }
    }



    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}