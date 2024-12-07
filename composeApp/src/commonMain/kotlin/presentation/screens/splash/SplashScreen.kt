package presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.jetbrains.compose.resources.painterResource

@Composable
fun SplashScreen(
    navController: NavController
) {
    val viewModel = remember { SplashViewModel() }
    val uiState by viewModel.uiState.collectAsState()

    when (uiState.appState) {
        AppState.Loading -> {
            LoadScreen()
        }

        AppState.Logged -> {
            navController.navigate("home")
        }

        AppState.Unlogged -> {
            navController.navigate("login")
        }
    }
}

@Composable
private fun LoadScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "Loading...",
            fontSize = 24.sp,
        )
    }
}