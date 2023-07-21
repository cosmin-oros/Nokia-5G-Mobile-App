package cosmin.dev.nokia5gmobileapp.utils

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cosmin.dev.nokia5gmobileapp.data.SharedPreferencesManager

@Composable
fun SpeedDisplay(speed: Float) {
    val progress by animateFloatAsState(targetValue = speed / MAX_SPEED)

    Text(
        text = "$speed km/h",
        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
    )

    Box(
        modifier = Modifier.size(200.dp)
    ) {
        
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            val centerX = canvasWidth / 2f
            val centerY = canvasHeight / 2f
            val radius = if (canvasWidth > canvasHeight) canvasHeight / 2f else canvasWidth / 2f

            // Draw the background circle
            drawCircle(
                color = Color.Gray,
                radius = radius,
                center = Offset(centerX, centerY),
                style = Stroke(width = 20.dp.toPx(), cap = Stroke.DefaultCap),
                alpha = 0.5f
            )

            // Draw the progress circle
            drawArc(
                color = Color.Green,
                startAngle = 270f,
                sweepAngle = 360f * progress,
                useCenter = false,
                topLeft = Offset(centerX - radius, centerY - radius),
                size = Size(radius * 2, radius * 2),
                style = Stroke(width = 20.dp.toPx(), cap = Stroke.DefaultCap),
                alpha = 0.8f
            )

            // Draw the center circle
            drawCircle(
                color = Color.White,
                radius = 50.dp.toPx(),
                center = Offset(centerX, centerY),
                style = Stroke(width = 20.dp.toPx(), cap = Stroke.DefaultCap),
            )

            // Draw the needle indicator
            val needleLength = radius - 40.dp.toPx()
            val needleAngle = 270f + (360f * progress)
            val needleX = centerX + needleLength * kotlin.math.cos(Math.toRadians(needleAngle.toDouble())).toFloat()
            val needleY = centerY + needleLength * kotlin.math.sin(Math.toRadians(needleAngle.toDouble())).toFloat()
            drawLine(
                color = Color.Red,
                start = Offset(centerX, centerY),
                end = Offset(needleX, needleY),
                strokeWidth = 8.dp.toPx(),
                cap = Stroke.DefaultCap
            )
        }

    }
}

private const val MAX_SPEED = 400f