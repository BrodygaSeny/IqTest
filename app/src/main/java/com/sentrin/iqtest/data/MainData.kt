package com.sentrin.iqtest.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sentrin.iqtest.MainActivityViewModel
import kotlinx.coroutines.*


class MainData(
){
    val questionRange = listOf(1..20, 50..58, 96..99)

    val rightAnswerTitleNumber:Int = 1
    val falseAnswerTitleNumber:Int = 1

    val startScore = 50
    val plusScore = 5
    val minusScore = 5


    // lateinit
    var contextMainActivity: Context? = null
    var database: MainDatabase? = null
    var viewModelMainActivity: MainActivityViewModel? = null

}



val userStatusList = mapOf(
   -100..-21 to "Простейшее",
   -20..-11 to "Банан",
   -10..-1 to "Одуванчик",
   0..9 to "Светлячок",
   10..19 to "Улитка",
   20..29 to "Рыбка",
   30..39 to "Ящерка",
   40..49 to "Черепаха",
   50..59 to "Сова",
   60..69 to "Чихуахуа",
   70..79 to "Енотик",
   80..89 to "Дельфин",
   90..99 to "Шимпанзе",
   100..109 to "Первобытный человек",
   110..129 to "Человек Разумный",
   130..149 to "Мегамозг",
   150 .. 1000 to "Доктор Стрэндж"
)



