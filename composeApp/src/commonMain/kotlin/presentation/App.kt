package presentation

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import presentation.screens.login.LoginScreen

@Composable
fun AppTheme() {
    MaterialTheme {
        Navigator(LoginScreen())
    }
}