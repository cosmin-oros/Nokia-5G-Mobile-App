package cosmin.dev.nokia5gmobileapp

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.preference.PreferenceManager
import cosmin.dev.nokia5gmobileapp.data.SharedPreferencesManager
import cosmin.dev.nokia5gmobileapp.navigation.Navigation
import cosmin.dev.nokia5gmobileapp.ui.theme.Nokia5GMobileAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Nokia5GMobileAppTheme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ){
                    Navigation(navController = navController)
                }

            }
        }
    }
}

