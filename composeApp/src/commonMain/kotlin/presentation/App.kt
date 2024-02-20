package presentation

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator

@Composable
fun AppTheme() {
    MaterialTheme {
        Navigator(
            LoginScreen()
        )
    }
}