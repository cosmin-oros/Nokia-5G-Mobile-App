package cosmin.dev.nokia5gmobileapp.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cosmin.dev.nokia5gmobileapp.R
import cosmin.dev.nokia5gmobileapp.data.SharedPreferencesManager
import cosmin.dev.nokia5gmobileapp.navigation.Screen
import cosmin.dev.nokia5gmobileapp.ui.theme.DarkGreen
import cosmin.dev.nokia5gmobileapp.ui.theme.LightGreen
import cosmin.dev.nokia5gmobileapp.utils.ColorOption

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomizeCarScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf(false) }
    var nameTooShortError by remember { mutableStateOf(false) }
    val colors = remember { mutableStateListOf(Color.Black, Color.Blue,
        Color.Cyan, Color.Red, Color.Yellow, Color.LightGray, Color.LightGray,
        Color.LightGray, Color.LightGray, Color.LightGray, Color.LightGray) }
    val selectedColor = remember { mutableStateOf(colors[0]) }
    val scrollState = rememberScrollState()

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
            Spacer(modifier = Modifier.height(32.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Logo",
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Please enter your car's info!",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Car Name:",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            TextField(
                value = name,
                onValueChange = {
                    name = it
                    nameError = false // Reset the last name error state when the input changes
                },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth(),
                isError = nameError, // Apply the error state to the TextField
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = if (nameError) Color.Red else DarkGreen, // Customize the outline color for error state
                    unfocusedIndicatorColor = if (nameError) Color.Red else DarkGreen // Customize the outline color for error state
                )
            )

            if (nameError) {
                Text(
                    text = "Please enter a valid name",
                    color = Color.Red,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            if (nameTooShortError) {
                Text(
                    text = "Please enter a longer name",
                    color = Color.Red,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Car Color:",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(colors) { color ->
                    ColorOption(color = color, isSelected = color == selectedColor.value) {
                        selectedColor.value = color
                    }
                }
            }


        }

        Button(
            onClick = {
                if (isNameValid(name)) {
                    if (name.length in 1..4) {
                        nameTooShortError = true
                    } else {
                        nameError = false
                        nameTooShortError = false
                        SharedPreferencesManager.setString("name", name)

                        when (selectedColor.value) {
                            Color.Black -> SharedPreferencesManager.setString("car_color", "black")
                            Color.Blue -> SharedPreferencesManager.setString("car_color", "blue")
                            Color.Cyan -> SharedPreferencesManager.setString("car_color", "cyan")
                            Color.Red -> SharedPreferencesManager.setString("car_color", "red")
                            else -> SharedPreferencesManager.setString("car_color", "default")
                        }

                        navController.navigate(Screen.TutorialScreen.route)
                    }
                } else {
                    nameError = true
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
                    text = "Next",
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White, fontSize = 18.sp),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

fun isNameValid(name: String): Boolean {
    return name.matches(Regex("[a-zA-Z]+")) // Check if the name contains only letters
}
