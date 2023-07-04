package cosmin.dev.nokia5gmobileapp.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cosmin.dev.nokia5gmobileapp.R
import cosmin.dev.nokia5gmobileapp.data.SharedPreferencesManager
import cosmin.dev.nokia5gmobileapp.navigation.Screen
import cosmin.dev.nokia5gmobileapp.ui.theme.DarkGreen
import cosmin.dev.nokia5gmobileapp.ui.theme.LightGreen

@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(bottom = 32.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "Profile Icon",
                    modifier = Modifier
                        .size(48.dp)
                        .clickable {
                            navController.navigate(Screen.ProfileScreen.route)
                        }
                        .padding(8.dp)
                )

                Icon(
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = "Settings Icon",
                    modifier = Modifier
                        .size(48.dp)
                        .clickable {
                            navController.navigate(Screen.SettingsScreen.route)
                        }
                        .padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // ! Change the logo image!!

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Logo",
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate(Screen.SinglePlayerScreen.route) },
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
                                    DarkGreen,
                                    LightGreen
                                )
                            )
                        )
                ) {
                    Text(
                        text = "Single Player",
                        style = MaterialTheme.typography.titleMedium.copy(color = Color.White, fontSize = 18.sp),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ! daca fac asta poate folosesc firebase si un cod de creare lobby

            Button(
                onClick = { },
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
                        text = "Multiplayer",
                        style = MaterialTheme.typography.titleMedium.copy(color = Color.White, fontSize = 18.sp),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { },
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
                        text = "Festival Mode",
                        style = MaterialTheme.typography.titleMedium.copy(color = Color.White, fontSize = 18.sp),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }

    }
}