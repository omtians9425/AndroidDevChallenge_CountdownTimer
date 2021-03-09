package com.example.androiddevchallenge.ui

import android.text.format.DateUtils
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class TimerViewModel : ViewModel() {

    var countedTimeSecs by mutableStateOf(60)
        private set

    var timerStarted by mutableStateOf(false)
        private set

    var countDownTimeText by mutableStateOf("")
        private set

    private val countDownTimeTextFlow: Flow<String> = flow {
        while (countedTimeSecs-- > 0) {
            emit(
                if (countedTimeSecs <= 60) countedTimeSecs.toString()
                else DateUtils.formatElapsedTime(countedTimeSecs.toLong())
            )
            delay(1000L)
        }
    }

    var timerJob: Job? = null

    private fun startTimer() {
        timerStarted = true

        timerJob = countDownTimeTextFlow.onEach {
            countDownTimeText = it
        }.launchIn(viewModelScope)
    }

    fun onFabClicked() {
        timerJob?.let {
            if(it.isActive) {
                it.cancel()
                timerStarted = false
                return
            }
        }
        startTimer()
    }
}
