import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import presentation.AppTheme

fun MainViewController() = ComposeUIViewController {
    AppTheme(
        dataStore = remember { createDataStore() }
    )
}
