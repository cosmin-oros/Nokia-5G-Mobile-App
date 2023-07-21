package cosmin.dev.nokia5gmobileapp.utils

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun SpeedDisplay(speed: Float) {
    Text(
        text = speed.toString(),
        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
    )
}