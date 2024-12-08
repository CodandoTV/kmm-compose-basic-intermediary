package presentation

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.first
import presentation.screens.forgotpassword.ForgotPasswordScreen
import presentation.screens.login.LoginScreen
import presentation.screens.splash.SplashScreen

object NavigationRoutes {
    const val Splash = "splash"
    const val Login = "login"
    const val ForgotPassword = "forgotpassword"
    const val Home = "home"
}

@Composable
fun AppTheme(
    dataStore: DataStore<Preferences>
) {
    val navController = rememberNavController()

    MaterialTheme {
        NavHost(
            navController = navController,
            startDestination = NavigationRoutes.Splash
        ) {
            composable(NavigationRoutes.Splash) {
               SplashScreen(navController, dataStore)
            }

            composable(NavigationRoutes.Login) {
                LoginScreen(navController, dataStore)
            }

            composable(NavigationRoutes.ForgotPassword) {
                ForgotPasswordScreen(navController)
            }

            composable(NavigationRoutes.Home) {
                ForgotPasswordScreen(navController)
            }
        }
    }
}