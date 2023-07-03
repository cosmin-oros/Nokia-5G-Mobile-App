package cosmin.dev.nokia5gmobileapp.ui.screens.main

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
    var carWithSetSpeedPosition by remember { mutableStateOf(0f) }

    val maxDistance = 220f // Maximum distance to the right

    // Calculate the dynamic distance based on the carWithSetSpeedPosition
    val dynamicDistance = (carWithSetSpeedPosition * 10).dp.coerceAtMost(maxDistance.dp)
    val dynamicDistanceYourCar = (carWithInternetPosition * 10).dp.coerceAtMost(maxDistance.dp)

    val connectivityManager = LocalContext.current.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    LaunchedEffect(Unit) {
        while (true) {
            val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

            // down - 94248 up - 9644
            val downloadSpeed = networkCapabilities?.linkDownstreamBandwidthKbps
            val uploadSpeed = networkCapabilities?.linkUpstreamBandwidthKbps

            val internetSpeed = (uploadSpeed?.toFloat()?.let { downloadSpeed?.toFloat()?.plus(it) }) ?: 0f // upload speed + download speed

            // still way too fast and need to fix the fact that the car gets smaller
            carWithInternetPosition += (internetSpeed / 10000000f)

            delay(10) // Delay to simulate the interval between measurements
        }
    }

    LaunchedEffect(Unit) {
        while (true) {
            // change these and make an option to select 4g/5g option when entering the screen
            val averageDownloadSpeed4g = 0f
            val averageUploadSpeed4g = 0f
            val averageDownloadSpeed5g = 0f
            val averageUploadSpeed5g = 0f

            // change 0.3f to averageDown + averageUp / 10000000f !!!
            carWithSetSpeedPosition += 0.003f // Adjust the set speed as needed
            delay(10) // Delay to control the speed of the game loop
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
        // !!! Make them move slowly but with the speeds set above until one of them reaches a certain point (left side of the screen
        // make the cars smaller in size
        // when they reach a certain point display a congratulations message

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = dynamicDistanceYourCar),
            horizontalArrangement = Arrangement.End
        ) {
            CarAnimation(color = SharedPreferencesManager.getString("car_color", "black"), size = 100.dp)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = dynamicDistance),
            horizontalArrangement = Arrangement.End
        ) {
            CarAnimation(color = "black", size = 100.dp)
        }

        if (carWithInternetPosition >= maxDistance || carWithSetSpeedPosition >= maxDistance) {
            val dialogShown = remember { mutableStateOf(false) }
            if (!dialogShown.value) {
                AlertDialog(
                    onDismissRequest = { dialogShown.value = false },
                    title = { Text("Congratulations!") },
                    text = { Text("You won!") },
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
                dialogShown.value = true
            }
        }
    }



}