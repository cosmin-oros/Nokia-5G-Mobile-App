package cosmin.dev.nokia5gmobileapp.ui.screens.other

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cosmin.dev.nokia5gmobileapp.R
import cosmin.dev.nokia5gmobileapp.data.SharedPreferencesManager
import cosmin.dev.nokia5gmobileapp.navigation.Screen
import cosmin.dev.nokia5gmobileapp.ui.screens.onboarding.isNameValid
import cosmin.dev.nokia5gmobileapp.ui.theme.DarkGreen
import cosmin.dev.nokia5gmobileapp.ui.theme.LightGreen
import cosmin.dev.nokia5gmobileapp.utils.ColorOption

@Composable
fun TutorialScreen(navController: NavController) {
    var counter by remember { mutableStateOf(0) }
    var language by remember { mutableStateOf(SharedPreferencesManager.getString("language", "english")) }
    var textValue by remember { mutableStateOf(if (language == "english") "You've successfully customized your car!"
                                            else "V-ați personalizat cu succes mașina!") }

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
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier.padding(bottom = 32.dp)) {
                Text(
                    text = textValue,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center, // Center align the text
                    modifier = Modifier.fillMaxWidth()
                )
            }


        }


        Button(
            onClick = {
                when (counter) {
                    0 -> {
                        textValue = if (language == "english") "You're now ready to race against an opponent!"
                                    else "Acum ești gata să concurezi împotriva unui adversar!"
                        counter++
                    }
                    1 -> {
                        textValue = if (language == "english") "Your speed will be determined by taking into account both your download and upload speeds!"
                                    else "Viteza ta va fi determinată luând în considerare atât viteza de descărcare, cât și cea de încărcare!"
                        counter++
                    }
                    2 -> {
                        textValue = if (language == "english") "You can choose the opponent's speed in the settings tab!"
                                    else "Puteți alege viteza adversarului în fila de setări!"
                        counter++
                    }
                    else -> {
                        if (SharedPreferencesManager.getString("logged_in", "no") == "yes") {
                            navController.navigate(Screen.MainScreen.route)
                        } else {
                            navController.navigate(Screen.CongratulationsScreen.route)
                        }
                    }
                }
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
                                DarkGreen,
                                LightGreen
                            )
                        )
                    )
            ) {
                Text(
                    text = if (language == "english") "Next" else "Mai departe",
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White, fontSize = 18.sp),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}