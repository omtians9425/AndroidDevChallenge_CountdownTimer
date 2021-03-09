package com.example.androiddevchallenge.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class TimerViewModel : ViewModel() {

    var countedTimeSecs by mutableStateOf(0)
        private set

    var timerStarted by mutableStateOf(false)
        private set

    private val countDownTimeFlow: Flow<Int> = flow {
        var seconds = 0
        while (seconds <= countedTimeSecs) {
            emit(seconds)
            seconds++
            delay(1000L)
        }
    }

    init {
        viewModelScope.launch {
            delay(1000L)
            countedTimeSecs = 40
            timerStarted = true
        }
    }
}
