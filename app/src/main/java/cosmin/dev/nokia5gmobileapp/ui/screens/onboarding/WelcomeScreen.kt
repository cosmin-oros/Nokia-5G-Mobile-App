package cosmin.dev.nokia5gmobileapp.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cosmin.dev.nokia5gmobileapp.R

@Composable
fun WelcomeScreen(navController: NavController) {
//    val sharedPrefs = SharedPreferencesManager.getSharedPrefs()
//
//    // Read the preference value
//    val value = SharedPreferencesManager.getString("key", "default value")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(bottom = 32.dp), // Add bottom padding to push the content up
        verticalArrangement = Arrangement.SpaceBetween, // Use SpaceBetween to position elements at top and bottom
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.Center, // Center the top content vertically
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
                text = "Nokia 5G/4G",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 32.dp)
            )
        }

        Button(
            // navigate
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(50.dp)
                .shadow(4.dp, shape = MaterialTheme.shapes.medium)
                .border(2.dp, Color.Cyan, shape = MaterialTheme.shapes.large),
            colors = ButtonDefaults.buttonColors(Color.Black),
        ) {
            Text(
                text = "Get Started",
                style = MaterialTheme.typography.titleMedium.copy(color = Color.White, fontSize = 18.sp)
            )
        }
    }
}