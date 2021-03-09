package com.example.androiddevchallenge.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class TimerViewModel : ViewModel() {

    private val maxTime = 50

    private val countDownTimeFlow: Flow<Int> = flow {
        var seconds = 0
        while (seconds < maxTime) {
            emit(seconds)
            seconds++
            delay(1000L)
        }
    }

    val timerSweepAngle: StateFlow<Float> = countDownTimeFlow.map { it * (360f / maxTime) }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = 0f
    )
}
