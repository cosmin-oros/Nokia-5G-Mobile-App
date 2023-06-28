package cosmin.dev.nokia5gmobileapp.utils

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

@SuppressLint("UnrememberedMutableState", "CoroutineCreationDuringComposition")
fun measureInternetSpeed(speedUpdate: (Float) -> Unit) {
    GlobalScope.launch(Dispatchers.Default) {
        while (true) {
            val internetSpeed = Random.nextFloat() * 100 // Simulate measuring internet speed

            withContext(Dispatchers.Main) {
                speedUpdate(internetSpeed)
            }

            delay(1000) // Delay to simulate the interval between measurements
        }
    }
}



