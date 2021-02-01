package com.sentrin.iqtest.ui.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sentrin.iqtest.data.MainEntity

class HomeViewModelFactory(
    val context: Context,
    val sentData:MainEntity?
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(context, sentData) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


