package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.purple200

@Composable
fun TimerScreen() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val arcSizePx = 300.dp.toPx()
        translate(size.width / 2, size.height / 2) {
            drawArc(
                color = purple200,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = 10f, cap = StrokeCap.Round),
                size = Size(arcSizePx, arcSizePx),
                topLeft = Offset(-(arcSizePx / 2), -(arcSizePx / 2))
            )
        }
    }
}