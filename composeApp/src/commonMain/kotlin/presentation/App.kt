package presentation

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.first
import org.koin.compose.KoinContext
import presentation.screens.detail.PostDetailScreen
import presentation.screens.feed.FeedScreen
import presentation.screens.forgotpassword.ForgotPasswordScreen
import presentation.screens.login.LoginScreen
import presentation.screens.splash.SplashScreen

object NavigationRoutes {
    const val Home: String = "home"
    const val Login = "login"
    const val ForgotPassword = "forgotpassword"
    const val Splash = "splash"
    const val Detail = "detail"
    const val PostIdArgument = "postId"
    const val PostDetailFullPath = "${Detail}/{$PostIdArgument}"
}

@Composable
fun AppTheme() {
    val navController = rememberNavController()
    KoinContext {
        MaterialTheme {
            NavHost(
                navController = navController,
                startDestination = NavigationRoutes.Splash
            ) {
                composable(NavigationRoutes.Splash) {
                    SplashScreen(navController)
                }

                composable(NavigationRoutes.Home) {
                    FeedScreen(navController)
                }

                composable(NavigationRoutes.PostDetailFullPath) {
                    PostDetailScreen(navController)
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
}