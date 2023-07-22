package cosmin.dev.nokia5gmobileapp.ui.screens.main

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.DialogHost
import cosmin.dev.nokia5gmobileapp.R
import cosmin.dev.nokia5gmobileapp.data.SharedPreferencesManager
import cosmin.dev.nokia5gmobileapp.navigation.Screen
import cosmin.dev.nokia5gmobileapp.utils.CarAnimation
import cosmin.dev.nokia5gmobileapp.utils.SpeedDisplay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@SuppressLint("UnrememberedMutableState", "CoroutineCreationDuringComposition")
@Composable
fun SinglePlayerScreen(navController: NavController) {
    var carWithInternetPosition by remember { mutableStateOf(0f) }
    var language by remember { mutableStateOf(SharedPreferencesManager.getString("language", "english")) }
    var score = 0f
    var speed by remember { mutableStateOf(0f) }
    var internetSpeed by remember { mutableStateOf(0f) }
    var gear by remember { mutableStateOf(0) }

    val maxDistance = 220f // Maximum distance to the right

    // Calculate the dynamic distance based on the carWithSetSpeedPosition
    val dynamicDistanceYourCar = (carWithInternetPosition * 10).dp.coerceAtMost(maxDistance.dp)

    val connectivityManager = LocalContext.current.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    LaunchedEffect(Unit) {
        while (true) {
            val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            // down - 94248 up - 9644
            val downloadSpeed = networkCapabilities?.linkDownstreamBandwidthKbps!!
            val uploadSpeed = networkCapabilities?.linkUpstreamBandwidthKbps!!

            internetSpeed = (uploadSpeed?.toFloat()?.let { downloadSpeed?.toFloat()?.plus(it) }) ?: 0f // upload speed + download speed

            when (gear) {
                0 -> {
                    gear++
                    delay(1000) // Delay to simulate the interval between measurements
                }
                1 -> {
                    carWithInternetPosition += (internetSpeed / 50000000f)
                    speed = internetSpeed / 5000f
                    gear++
                    delay(500) // Delay to simulate the interval between measurements
                }
                2 -> {
                    carWithInternetPosition += (internetSpeed / 40000000f)
                    speed = internetSpeed / 4000f
                    gear++
                    delay(500) // Delay to simulate the interval between measurements
                }
                3 -> {
                    carWithInternetPosition += (internetSpeed / 30000000f)
                    speed = internetSpeed / 3000f
                    gear++
                    delay(500) // Delay to simulate the interval between measurements
                }
                4 -> {
                    carWithInternetPosition += (internetSpeed / 20000000f)
                    speed = internetSpeed / 2000f
                    gear++
                    delay(500) // Delay to simulate the interval between measurements
                }
                5 -> {
                    carWithInternetPosition += (internetSpeed / 15000000f)
                    speed = internetSpeed / 1500f
                    gear++
                    delay(500) // Delay to simulate the interval between measurements
                }
                else -> {
                    carWithInternetPosition += (internetSpeed / 10000000f)
                    speed = internetSpeed / 1000f
                    delay(10) // Delay to simulate the interval between measurements
                }
            }

            score = speed

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        SpeedDisplay(speed = speed)

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(bottom = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = dynamicDistanceYourCar),
                horizontalArrangement = Arrangement.End
            ) {
                CarAnimation(color = SharedPreferencesManager.getString("car_color", "black"), size = 100.dp, isOpponent = false)
            }

            if (carWithInternetPosition >= 21f) {
                val dialogShown = remember { mutableStateOf(false) }
                if (!dialogShown.value) {
                    LaunchedEffect(Unit) {
                        delay(1000) // Delay to ensure the dialog is shown after the composition is committed
                        dialogShown.value = true
                    }
                }

                if (dialogShown.value) {
                    AlertDialog(
                        onDismissRequest = { dialogShown.value = false },
                        title = { Text(if (language == "english") "Congratulations!" else "Felicitari!") },
                        text = { Text(if (language == "english") "Your score: $score" else "Scorul tau: $score") },
                        confirmButton = {
                            Button(
                                onClick = {
                                    dialogShown.value = false
                                    navController.navigate(Screen.MainScreen.route)
                                }
                            ) {
                                Text("OK")
                            }
                        }
                    )
                }
            }
        }
    }

}