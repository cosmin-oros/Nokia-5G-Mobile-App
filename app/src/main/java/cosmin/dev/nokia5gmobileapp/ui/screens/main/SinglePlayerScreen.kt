package cosmin.dev.nokia5gmobileapp.ui.screens.main

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cosmin.dev.nokia5gmobileapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableState", "CoroutineCreationDuringComposition")
@Composable
fun SinglePlayerScreen(navController: NavController) {
    var carWithInternetPosition by remember { mutableStateOf(0f) }
    var carWithSetSpeedPosition by remember { mutableStateOf(0f) }

    val carImage = painterResource(R.drawable.ic_launcher_foreground) // Replace with your car image resource
    val canvasSize = 300.dp

    val connectivityManager = LocalContext.current.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    LaunchedEffect(Unit) {
        while (true) {
            val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

            val downloadSpeed = networkCapabilities?.linkDownstreamBandwidthKbps
            val uploadSpeed = networkCapabilities?.linkUpstreamBandwidthKbps

            val internetSpeed = downloadSpeed?.toFloat() ?: 0f // Change to use both download and upload speeds

            carWithInternetPosition = internetSpeed / 10f

            delay(1000) // Delay to simulate the interval between measurements
        }
    }

    LaunchedEffect(Unit) {
        while (true) {
            carWithSetSpeedPosition += 5f // Adjust the set speed as needed
            delay(16) // Delay to control the speed of the game loop
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawRect(Color.White, size = size)

        val carImageBitmap = carImage as ImageBitmap

        val carWidth = carImageBitmap.width.toFloat()
        val carHeight = carImageBitmap.height.toFloat()

        val carWithInternetX = (size.width - carWidth) * carWithInternetPosition / 100f
        val carWithSetSpeedX = (size.width - carWidth) * carWithSetSpeedPosition / 100f

        drawImage(carImageBitmap, Offset(carWithInternetX, 100f))
        drawImage(carImageBitmap, Offset(carWithSetSpeedX, 200f))
    }
}