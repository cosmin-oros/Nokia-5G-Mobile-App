package cosmin.dev.nokia5gmobileapp.utils

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import cosmin.dev.nokia5gmobileapp.R

@Composable
fun CarAnimation(color: String) {
    val compositionRed by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.car_red))
    val compositionCyan by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.car_blue1))
    val compositionBlue by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.car_blue2))
    val compositionBlack by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.car_black))
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.car))

    when (color) {
        "black" -> LottieAnimation(
            modifier = Modifier.size(250.dp),
            composition = compositionBlack,
            iterations = LottieConstants.IterateForever
        )
        "blue" -> LottieAnimation(
            modifier = Modifier.size(250.dp),
            composition = compositionBlue,
            iterations = LottieConstants.IterateForever
        )
        "cyan" -> LottieAnimation(
            modifier = Modifier.size(250.dp),
            composition = compositionCyan,
            iterations = LottieConstants.IterateForever
        )
        "red" -> LottieAnimation(
            modifier = Modifier.size(250.dp),
            composition = compositionRed,
            iterations = LottieConstants.IterateForever
        )
        else -> LottieAnimation(
            modifier = Modifier.size(250.dp),
            composition = composition,
            iterations = LottieConstants.IterateForever
        )
    }


}