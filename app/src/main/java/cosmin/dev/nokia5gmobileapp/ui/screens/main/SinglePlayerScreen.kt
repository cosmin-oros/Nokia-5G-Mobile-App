package cosmin.dev.nokia5gmobileapp.ui.screens.main

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
    var carPosition by remember { mutableStateOf(0f) }
    var language by remember { mutableStateOf(SharedPreferencesManager.getString("language", "english")) }
    var score by remember { mutableStateOf(0f) }
    var speed by remember { mutableStateOf(0f) }
    var internetSpeed by remember { mutableStateOf(0f) }
    var gear by remember { mutableStateOf(0) }

    val maxDistance = 220f // Maximum distance to the right

    // Calculate the dynamic distance based on the carPosition
    val dynamicDistanceYourCar = (carPosition * 10).dp.coerceAtMost(maxDistance.dp)

    val connectivityManager = LocalContext.current.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    LaunchedEffect(Unit) {
        while (true) {
            val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            val downloadSpeed = networkCapabilities?.linkDownstreamBandwidthKbps ?: 0
            val uploadSpeed = networkCapabilities?.linkUpstreamBandwidthKbps ?: 0

            internetSpeed = (uploadSpeed.toFloat() + downloadSpeed.toFloat())

            when (gear) {
                0 -> {
                    gear++
                    delay(100) // Delay to simulate the interval between measurements
                }
                1 -> {
                    carPosition += (internetSpeed * 3 / 50000000f)
                    speed = internetSpeed * 3 / 5000f
                    gear++
                    delay(50) // Delay to simulate the interval between measurements
                }
                2 -> {
                    carPosition += (internetSpeed * 3 / 40000000f)
                    speed = internetSpeed * 3 / 4000f
                    gear++
                    delay(50) // Delay to simulate the interval between measurements
                }
                // Add more cases for gear 3, 4, 5, etc.
                else -> {
                    carPosition += (internetSpeed * 3 / 10000000f)
                    speed = internetSpeed * 3 / 1000f
                    delay(10) // Delay to simulate the interval between measurements
                }
            }

            if (carPosition <= 21f) {
                score = speed
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
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

            if (carPosition >= 21f) {
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
