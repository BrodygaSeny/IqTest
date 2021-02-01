package com.sentrin.iqtest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(): ViewModel(){
    var showPromptStairs = MutableLiveData<Boolean>()
    var showPromptThinkAbout = MutableLiveData<Boolean>()

    init {
        showPromptStairs.value = false
        showPromptThinkAbout.value = false
    }

}