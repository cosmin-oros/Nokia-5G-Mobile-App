package cosmin.dev.nokia5gmobileapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import cosmin.dev.nokia5gmobileapp.ui.screens.main.MainScreen
import cosmin.dev.nokia5gmobileapp.ui.screens.onboarding.CustomizeCarScreen
import cosmin.dev.nokia5gmobileapp.ui.screens.onboarding.WelcomeScreen
import cosmin.dev.nokia5gmobileapp.ui.screens.other.SplashScreen

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
//
//        composable(Screen.CongratulationsScreen.route) {
//            CongratulationsScreen(navController = navController)
//        }

        composable(Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }

    }
}