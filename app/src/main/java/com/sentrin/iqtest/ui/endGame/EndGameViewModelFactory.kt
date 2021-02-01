package com.sentrin.iqtest.ui.endGame

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sentrin.iqtest.data.MainEntity

class EndGameViewModelFactory (
val context: Context,
var sentData:MainEntity
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EndGameViewModel::class.java)) {
            return EndGameViewModel(context, sentData) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}