package com.myapp.millionaire.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


open class BaseViewModel : ViewModel(){
    val backendError = MutableLiveData<String>()
}
