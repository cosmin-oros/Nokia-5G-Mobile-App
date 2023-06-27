package cosmin.dev.nokia5gmobileapp.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cosmin.dev.nokia5gmobileapp.ui.theme.DarkGreen

@Composable
fun ColorOption(color: Color, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(40.dp)
            .background(color = color, shape = MaterialTheme.shapes.small)
            .border(
                width = 2.dp,
                color = if (isSelected) DarkGreen else Color.Transparent,
                shape = MaterialTheme.shapes.small
            )
            .clickable { onClick() }
    )
}