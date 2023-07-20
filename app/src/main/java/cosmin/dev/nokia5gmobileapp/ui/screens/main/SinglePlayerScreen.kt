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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.DialogHost
import cosmin.dev.nokia5gmobileapp.R
import cosmin.dev.nokia5gmobileapp.data.SharedPreferencesManager
import cosmin.dev.nokia5gmobileapp.navigation.Screen
import cosmin.dev.nokia5gmobileapp.utils.CarAnimation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableState", "CoroutineCreationDuringComposition")
@Composable
fun SinglePlayerScreen(navController: NavController) {
    var carWithInternetPosition by remember { mutableStateOf(0f) }
    var language by remember { mutableStateOf(SharedPreferencesManager.getString("language", "english")) }

    val maxDistance = 220f // Maximum distance to the right

    // Calculate the dynamic distance based on the carWithSetSpeedPosition
    val dynamicDistanceYourCar = (carWithInternetPosition * 10).dp.coerceAtMost(maxDistance.dp)

    val connectivityManager = LocalContext.current.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    LaunchedEffect(Unit) {
        while (true) {
            val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

            // down - 94248 up - 9644
            val downloadSpeed = networkCapabilities?.linkDownstreamBandwidthKbps
            val uploadSpeed = networkCapabilities?.linkUpstreamBandwidthKbps

            val internetSpeed = (uploadSpeed?.toFloat()?.let { downloadSpeed?.toFloat()?.plus(it) }) ?: 0f // upload speed + download speed

            carWithInternetPosition += (internetSpeed / 10000000f)

            delay(10) // Delay to simulate the interval between measurements
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

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = dynamicDistanceYourCar),
            horizontalArrangement = Arrangement.End
        ) {
            CarAnimation(color = SharedPreferencesManager.getString("car_color", "black"), size = 200.dp, isOpponent = false)
        }

        if (carWithInternetPosition >= 20f) {
            val dialogShown = remember { mutableStateOf(false) }
            if (!dialogShown.value) {
                LaunchedEffect(Unit) {
                    delay(1000) // Delay to ensure the dialog is shown after the composition is committed
                    dialogShown.value = true
                }
            }

            if (dialogShown.value) {
                // change condition !!! display km/h and a score/time at the end, change size etc
                if (carWithInternetPosition > 1) {
                    AlertDialog(
                        onDismissRequest = { dialogShown.value = false },
                        title = { Text(if (language == "english") "Congratulations!" else "Felicitari!") },
                        text = { Text(if (language == "english") "You won!" else "Ai castigat!") },
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
                } else {
                    AlertDialog(
                        onDismissRequest = { dialogShown.value = false },
                        title = { Text(if (language == "english") "Better luck next time!" else "Mai mult noroc data viitoare!") },
                        text = { Text(if (language == "english") "You lost!" else "Ai pierdut!") },
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