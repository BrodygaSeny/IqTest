package com.sentrin.iqtest.ui.firstStart

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FirstStartViewModelFactory(
    val context: Context
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FirstStartViewModel::class.java)) {
            return FirstStartViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
