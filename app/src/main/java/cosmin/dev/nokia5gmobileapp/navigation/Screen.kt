package cosmin.dev.nokia5gmobileapp.navigation

const val MACROS_ARGUMENT_KEY = "id"

sealed class Screen(val route: String){
    object SplashScreen: Screen("splash_screen")
    object WelcomeScreen: Screen("welcome_screen")
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