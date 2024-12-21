package presentation.screens.feed

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun FeedScreen(
    navController: NavController
) {
    Text(
        text = "Feed Screen",
        fontSize = 56.sp
    )
}