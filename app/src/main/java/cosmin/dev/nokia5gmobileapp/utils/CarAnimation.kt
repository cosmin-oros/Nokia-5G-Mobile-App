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
    val compositionRed by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.car1))
    val composition2 by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.car2))
    val composition3 by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.car3))
    val composition4 by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.car4))
    val composition5 by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.car5))
    val composition6 by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.car6))
    val composition7 by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.car7))
    val composition8 by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.car8))
    val composition9 by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.car9))

//    if sau when pt coloru primit, momentan cred ca toate is la fel animatiile trb sa le descarc iar

    LottieAnimation(
        modifier = Modifier.size(250.dp),
        composition = composition5,
        iterations = LottieConstants.IterateForever
    )
}