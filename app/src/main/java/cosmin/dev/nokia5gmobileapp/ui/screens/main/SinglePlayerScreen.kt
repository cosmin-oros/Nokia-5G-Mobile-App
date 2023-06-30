package cosmin.dev.nokia5gmobileapp.ui.screens.main

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cosmin.dev.nokia5gmobileapp.R
import cosmin.dev.nokia5gmobileapp.data.SharedPreferencesManager
import cosmin.dev.nokia5gmobileapp.utils.CarAnimation
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(bottom = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End
    ) {
        CarAnimation(color = SharedPreferencesManager.getString("car_color", "black"))

        Spacer(modifier = Modifier.height(32.dp))

        CarAnimation(color = "black")
    }

}