package cosmin.dev.nokia5gmobileapp.ui.screens.main

import android.widget.ToggleButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cosmin.dev.nokia5gmobileapp.data.SharedPreferencesManager
import cosmin.dev.nokia5gmobileapp.navigation.Screen

// log out and etc
@Composable
fun SettingsScreen(navController: NavController) {
    var selectedButton by remember { mutableStateOf(SharedPreferencesManager.getString("opponent_speed", "4g")) }
    var language by remember { mutableStateOf(SharedPreferencesManager.getString("language", "english")) }

    // make something to access the tutorial again

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.navigateUp() },
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Back"
                )
            }

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Settings",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Opponent Network Speed",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally,) {
                    Text(
                        text = "4G",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    RadioButton(
                        selected = selectedButton == "4g",
                        onClick = {
                            selectedButton = "4g"
                            SharedPreferencesManager.setString("opponent_speed", "4g")
                        },
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally,) {
                    Text(
                        text = "5G",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    RadioButton(
                        selected = selectedButton == "5g",
                        onClick = {
                            selectedButton = "5g"
                            SharedPreferencesManager.setString("opponent_speed", "5g")
                        },
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }


        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                SharedPreferencesManager.setString("logged_in", "no")
                navController.navigate(Screen.WelcomeScreen.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(50.dp)
                .shadow(4.dp, shape = MaterialTheme.shapes.medium),
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            contentPadding = PaddingValues(0.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.DarkGray,
                                Color.LightGray
                            )
                        )
                    )
            ) {
                Text(
                    text = "Logout",
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White, fontSize = 18.sp),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}