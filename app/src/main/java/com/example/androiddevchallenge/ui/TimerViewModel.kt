package com.example.androiddevchallenge.ui

import android.text.format.DateUtils
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class TimerViewModel : ViewModel() {

    var countedTimeSecs by mutableStateOf(0)
        private set

    var timerStarted by mutableStateOf(false)
        private set

    private val countDownTimeTextFlow: Flow<String> = flow {
        countedTimeSecs = 60
        timerStarted = true

        var seconds = countedTimeSecs
        while (seconds-- > 0) {
            emit(
                if (seconds <= 60) seconds.toString()
                else DateUtils.formatElapsedTime(seconds.toLong())
            )
            delay(1000L)
        }
    }

    val countDownTimeText: StateFlow<String> = countDownTimeTextFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = "0:00"
    )
}
