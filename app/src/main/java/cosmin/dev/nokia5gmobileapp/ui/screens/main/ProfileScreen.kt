package cosmin.dev.nokia5gmobileapp.ui.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cosmin.dev.nokia5gmobileapp.data.SharedPreferencesManager
import cosmin.dev.nokia5gmobileapp.utils.CarAnimation

// retrieve info from shared pref
@Composable
fun ProfileScreen(navController: NavController) {
    // display car name, color, how many times you won etc
    CarAnimation(color = SharedPreferencesManager.getString("car_color", "black"), size = 250.dp, isOpponent = false)
}