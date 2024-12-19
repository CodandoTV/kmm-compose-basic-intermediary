package presentation

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import presentation.screens.feed.FeedScreen
import org.koin.compose.KoinContext
import presentation.screens.forgotpassword.ForgotPasswordScreen
import presentation.screens.login.LoginScreen
import presentation.screens.splash.SplashScreen
import presentation.screens.postdetail.PostDetailScreen
import presentation.screens.splash.SplashScreen

object NavigationRoutes {
    const val Splash = "splash"
    const val Login = "login"
    const val ForgotPassword = "forgotpassword"
    const val Feed = "feed"
    const val PostDetail = "postDetail"
    const val PostIdArgument = "postId"
    const val PostDetailFullPath = "${PostDetail}/{$PostIdArgument}"

}

@Composable
fun AppTheme(
    dataStore: DataStore<Preferences>
) {
    val navController = rememberNavController()
    KoinContext {
        MaterialTheme {
            NavHost(
                navController = navController,
                startDestination = NavigationRoutes.Splash
            ) {
                composable(NavigationRoutes.Splash) {
                    SplashScreen(navController, dataStore)
                }

                composable(NavigationRoutes.Login) {
                    LoginScreen(navController)
                }

                composable(NavigationRoutes.ForgotPassword) {
                    ForgotPasswordScreen(navController)
                }
            }

            composable(NavigationRoutes.Feed) {
                FeedScreen(navController)
            }

            composable(NavigationRoutes.PostDetailFullPath) {
                PostDetailScreen(navController)
            }
        }
    }
}
}