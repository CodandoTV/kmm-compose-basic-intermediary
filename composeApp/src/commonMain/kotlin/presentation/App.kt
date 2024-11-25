package presentation

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import presentation.screens.login.LoginScreen

@Composable
fun AppTheme() {
    MaterialTheme {
        LoginScreen()
    }
}