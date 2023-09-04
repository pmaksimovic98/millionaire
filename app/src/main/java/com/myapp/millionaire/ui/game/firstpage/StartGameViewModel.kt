package com.myapp.millionaire.ui.game.firstpage

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.myapp.millionaire.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartGameViewModel
@Inject constructor() : BaseViewModel() {
    val progress = MutableLiveData<Int>()
    val finished = MutableLiveData<Boolean>()


    fun startProgressBar() {
        viewModelScope.launch {
            progress.value = 0
            val timer = object : CountDownTimer(1000, 10) {
                override fun onTick(millisUntilFinished: Long) {
                    progress.value = progress.value?.plus(2)
                }

                override fun onFinish() {
                    finished.value = true
                }
            }
            timer.start()

        }

    }
}