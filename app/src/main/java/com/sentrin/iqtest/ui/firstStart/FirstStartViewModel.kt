package com.sentrin.iqtest.ui.firstStart

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sentrin.iqtest.MyApplication.Companion.mainData
import com.sentrin.iqtest.data.MainEntity
import com.sentrin.iqtest.data.getDatabase
import kotlinx.coroutines.*

class FirstStartViewModel(val context: Context): ViewModel() {
    private val database = getDatabase(context)
    var goToQuest = MutableLiveData<Boolean>()
    var sentData:MainEntity? = null
    var darkTheme = MutableLiveData<Boolean>()

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main )


    init{
        darkTheme.value = false
    }

    fun onStart(){
        goToQuest.value = true
    }

    fun saveInDatabase(name:String){
        sentData = MainEntity(0, mainData.startScore, name, mutableListOf(), darkTheme.value!!)
        viewModelScope.launch {
                insertData(sentData!!)
        }
    }

//

    suspend fun getData(): MainEntity?{
        return withContext(Dispatchers.IO) {
            database.mainEntityDao.getAll().run {
                if (isEmpty()) null
                else first()
            }
        }
    }

    suspend fun insertData(data: MainEntity){
        withContext(Dispatchers.IO) {
            database.mainEntityDao.insertAll(listOf(data))
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}