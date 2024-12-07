package presentation

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import presentation.screens.forgotpassword.ForgotPasswordScreen
import presentation.screens.login.LoginScreen
import presentation.screens.splash.SplashScreen

object NavigationRoutes {
    const val Splash = "splash"
    const val Login = "login"
    const val ForgotPassword = "forgotpassword"
}

@Composable
fun AppTheme() {
    val navController = rememberNavController()

    MaterialTheme {
        NavHost(
            navController = navController,
            startDestination = NavigationRoutes.Splash
        ) {
            composable(NavigationRoutes.Splash) {
               SplashScreen(navController)
            }

            composable(NavigationRoutes.Login) {
                LoginScreen(navController)
            }

            composable(NavigationRoutes.ForgotPassword) {
                ForgotPasswordScreen(navController)
            }
        }
    }
}