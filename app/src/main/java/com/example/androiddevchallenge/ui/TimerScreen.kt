package com.example.androiddevchallenge.ui

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.ui.theme.purple200

@Composable
fun TimerScreen() {
    val viewModel = viewModel<TimerViewModel>()
    val sweepAngle: Float by animateFloatAsState(
        targetValue = if (viewModel.isTimerRunning) -360f else 0f,
        animationSpec = tween(
            durationMillis = viewModel.countedTimeSecs * 1000,
            easing = LinearEasing
        )
    )
    StatelessTimerScreen(
        sweepAngle = sweepAngle,
        countDownTime = viewModel.countDownTimeText,
        timerStarted = viewModel.isTimerRunning,
        onFabClicked = viewModel::onFabClicked
    )
}

@Composable
fun StatelessTimerScreen(
    sweepAngle: Float,
    countDownTime: String,
    timerStarted: Boolean,
    onFabClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Transparent) {
                Text(
                    text = "Timer",
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = MaterialTheme.colors.primary,
                onClick = onFabClicked,
            ) {
                if (timerStarted) {
                    Icon(Icons.Default.Pause, contentDescription = "Pause timer")
                } else {
                    Icon(Icons.Default.PlayArrow, contentDescription = "Start timer")
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Box(contentAlignment = Alignment.Center) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val arcSizePx = 300.dp.toPx()
                translate(size.width / 2, size.height / 2) {
                    drawArc(
                        color = Color.White,
                        startAngle = 0f,
                        sweepAngle = 360f,
                        useCenter = false,
                        style = Stroke(width = 8.dp.toPx()),
                        size = Size(arcSizePx, arcSizePx),
                        topLeft = Offset(-(arcSizePx / 2), -(arcSizePx / 2))
                    )
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
                fontSize = 48.sp
            )
        }
    }
}