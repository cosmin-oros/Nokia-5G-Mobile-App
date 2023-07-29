package cosmin.dev.nokia5gmobileapp.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import cosmin.dev.nokia5gmobileapp.R
import cosmin.dev.nokia5gmobileapp.data.SharedPreferencesManager

@Composable
fun CarAnimation(color: String, size: Dp, isOpponent: Boolean) {
    val compositionRed by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.car_red))
    val compositionCyan by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.car_blue1))
    val compositionBlue by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.car_blue2))
    val compositionBlack by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.car_black))
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.car))

    Box(
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            modifier = Modifier.size(size),
            composition = when (color) {
                "black" -> compositionBlack
                "blue" -> compositionBlue
                "cyan" -> compositionCyan
                "red" -> compositionRed
                else -> composition
            },
            iterations = LottieConstants.IterateForever
        )

        Column(
            modifier = Modifier.padding(bottom = 90.dp),
        ) {
            Text(
                text = SharedPreferencesManager.getString("name", "name"),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            )
        }
    }


}