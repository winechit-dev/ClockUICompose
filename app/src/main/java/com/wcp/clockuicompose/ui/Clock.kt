package com.wcp.clockuicompose.ui

import android.graphics.Color
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wcp.clockuicompose.ui.theme.Black
import com.wcp.clockuicompose.ui.theme.Gray
import java.lang.Math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Clock(
    seconds: Float = 0f,
    minutes: Float = 0f,
    hours: Float = 0f,
    radius: Dp = 100.dp
) {
    Canvas(modifier = Modifier.size(radius * 2f)) {
        drawContext.canvas.nativeCanvas.apply {
            drawCircle(
                center.x,
                center.y,
                radius.toPx(),
                Paint().apply {
                    color = Color.WHITE
                    setShadowLayer(
                        50f,
                        0f,
                        0f,
                        Color.argb(50, 0, 0, 0)
                    )
                }
            )
        }

        // Lines
        for (i in 0..59) {
            val angleInRad = i * (360f / 60f) * (PI.toFloat() / 180f)
            val lineLength = if (i % 5 == 0) 20.dp.toPx() else 15.dp.toPx()
            val strokeWidth = if (i % 5 == 0) 1.dp.toPx() else 0.5.dp.toPx()
            val color = if(i % 5 == 0) Gray else Black

            val lineStart = Offset(
                x = radius.toPx() * cos(angleInRad) + center.x,
                y = radius.toPx() * sin(angleInRad) + center.y
            )
            val lineEnd = Offset(
                x = (radius.toPx() - lineLength) * cos(angleInRad) + center.x,
                y = (radius.toPx() - lineLength) * sin(angleInRad) + center.y
            )
            drawLine(
                color = color,
                start = lineStart,
                end = lineEnd,
                strokeWidth = strokeWidth
            )
        }

        // Seconds
        rotate(degrees = seconds * (360f / 60f)) {
            drawLine(
                color = androidx.compose.ui.graphics.Color.Red,
                start = center,
                end = Offset(center.x, 20.dp.toPx()),
                strokeWidth = 2.dp.toPx(),
                cap = StrokeCap.Round
            )
        }
        // Minutes
        rotate(degrees = minutes * (360f / 60f)) {
            drawLine(
                color = androidx.compose.ui.graphics.Color.Black,
                start = center,
                end = Offset(center.x, 20.dp.toPx()),
                strokeWidth = 3.dp.toPx(),
                cap = StrokeCap.Round
            )
        }
        // Hours
        rotate(degrees = hours * (360f / 12f)) {
            drawLine(
                color = androidx.compose.ui.graphics.Color.Black,
                start = center,
                end = Offset(center.x, 35.dp.toPx()),
                strokeWidth = 4.dp.toPx(),
                cap = StrokeCap.Round
            )
        }
    }
}