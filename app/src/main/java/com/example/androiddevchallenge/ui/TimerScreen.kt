package com.example.androiddevchallenge.ui

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.ui.theme.purple200

@Composable
fun TimerScreen() {
    val viewModel = viewModel<TimerViewModel>()
    StateLessTimerScreen(
        duration = viewModel.countedTimeSecs,
        timerStarted = viewModel.timerStarted
    )
}

@Composable
fun StateLessTimerScreen(duration: Int, timerStarted: Boolean) {
    val angle: Float by animateFloatAsState(
        targetValue = if (timerStarted) 360f else 0f,
        animationSpec = tween(durationMillis = duration * 1000, easing = LinearEasing)
    )
    Log.d("tag", "duration: $duration, timerStarted: $timerStarted, angle: $angle")
    Canvas(modifier = Modifier.fillMaxSize()) {
        val arcSizePx = 300.dp.toPx()
        translate(size.width / 2, size.height / 2) {
            drawArc(
                color = purple200,
                startAngle = -90f,
                sweepAngle = angle,
                useCenter = false,
                style = Stroke(width = 10f, cap = StrokeCap.Round),
                size = Size(arcSizePx, arcSizePx),
                topLeft = Offset(-(arcSizePx / 2), -(arcSizePx / 2))
            )
        }
    }
}