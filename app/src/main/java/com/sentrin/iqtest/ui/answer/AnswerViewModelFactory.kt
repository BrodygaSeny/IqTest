package com.sentrin.iqtest.ui.answer

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sentrin.iqtest.data.MainEntity
import com.sentrin.iqtest.data.Question

class AnswerViewModelFactory(
    val context: Context,
    val question: Question,
    val isAnswerRight:Boolean,
    var sentData:MainEntity
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnswerViewModel::class.java)) {
            return AnswerViewModel(context, question, isAnswerRight, sentData) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}