package com.example.androiddevchallenge.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*


class TimerViewModel : ViewModel() {

    private val countDownTimeFlow: Flow<Int> = flow {
        var count = 0
        while (true) {
            emit(count)
            count++
            kotlinx.coroutines.delay(1000L)
        }
    }

    val timerSweepAngle: StateFlow<Float> = countDownTimeFlow.map { it / 60f }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = 0f
    )
}
