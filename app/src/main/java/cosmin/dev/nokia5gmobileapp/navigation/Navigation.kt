package cosmin.dev.nokia5gmobileapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import cosmin.dev.nokia5gmobileapp.ui.screens.main.MainScreen
import cosmin.dev.nokia5gmobileapp.ui.screens.main.ProfileScreen
import cosmin.dev.nokia5gmobileapp.ui.screens.main.SettingsScreen
import cosmin.dev.nokia5gmobileapp.ui.screens.main.SinglePlayerScreen
import cosmin.dev.nokia5gmobileapp.ui.screens.onboarding.CongratulationsScreen
import cosmin.dev.nokia5gmobileapp.ui.screens.onboarding.CustomizeCarScreen
import cosmin.dev.nokia5gmobileapp.ui.screens.onboarding.WelcomeScreen
import cosmin.dev.nokia5gmobileapp.ui.screens.other.SplashScreen
import cosmin.dev.nokia5gmobileapp.ui.screens.other.TutorialScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route){
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }

        composable(Screen.WelcomeScreen.route) {
            WelcomeScreen(navController = navController)
        }

        composable(Screen.CustomizeCarScreen.route) {
            CustomizeCarScreen(navController = navController)
        }

        composable(Screen.CongratulationsScreen.route) {
            CongratulationsScreen(navController = navController)
        }

        composable(Screen.TutorialScreen.route) {
            TutorialScreen(navController = navController)
        }

        composable(Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }

        composable(Screen.SinglePlayerScreen.route) {
            SinglePlayerScreen(navController = navController)
        }

        composable(Screen.SettingsScreen.route) {
            SettingsScreen(navController = navController)
        }

        composable(Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }

    }
}