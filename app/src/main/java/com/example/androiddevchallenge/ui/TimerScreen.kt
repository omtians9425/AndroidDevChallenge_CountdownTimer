package com.example.androiddevchallenge.ui

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.ui.theme.purple200

@Composable
fun TimerScreen() {
    val viewModel = viewModel<TimerViewModel>()
    val sweepAngle: Float by animateFloatAsState(
        targetValue = if (viewModel.timerStarted) 360f else 0f,
        animationSpec = tween(
            durationMillis = viewModel.countedTimeSecs * 1000,
            easing = LinearEasing
        )
    )
    val countdownTimeText by viewModel.countDownTimeText.collectAsState()
    StateLessTimerScreen(sweepAngle = sweepAngle, countDownTime = countdownTimeText)
}

@Composable
fun StateLessTimerScreen(sweepAngle: Float, countDownTime: String) {
    Box(contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val arcSizePx = 300.dp.toPx()
            translate(size.width / 2, size.height / 2) {
                drawArc(
                    color = purple200,
                    startAngle = -90f,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(width = 8.dp.toPx(), cap = StrokeCap.Round),
                    size = Size(arcSizePx, arcSizePx),
                    topLeft = Offset(-(arcSizePx / 2), -(arcSizePx / 2))
                )
            }
        }
        Text(
            text = countDownTime,
            color = MaterialTheme.colors.primary,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold
        )
    }
}