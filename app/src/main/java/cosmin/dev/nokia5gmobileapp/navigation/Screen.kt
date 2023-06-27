package cosmin.dev.nokia5gmobileapp.navigation

const val MACROS_ARGUMENT_KEY = "id"

sealed class Screen(val route: String){
    object SplashScreen: Screen("splash_screen")
    object TutorialScreen: Screen("tutorial_screen")
    object WelcomeScreen: Screen("welcome_screen")
    object CustomizeCarScreen: Screen("customize_car_screen")
    object CongratulationsScreen: Screen("congratulations_screen")
    object MainScreen: Screen("main_screen")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach { arg->
                append("/$arg")
            }
        }
    }
}