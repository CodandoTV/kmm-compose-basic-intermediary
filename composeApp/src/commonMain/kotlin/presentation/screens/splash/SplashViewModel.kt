package presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.LoginRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import presentation.screens.login.LoginUIState

data class SlashUiState(
    val appState: AppState = AppState.Loading
)


sealed class AppState {
    data object Loading : AppState()
    data object LoggedIn : AppState()
    data object NotLoggedIn : AppState()
}

class SplashViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(SlashUiState())
    val uiState: StateFlow<SlashUiState>
        get() = _uiState

    init {
        viewModelScope.launch {
            delay(2000)
            _uiState.value = _uiState.value.copy(appState = AppState.LoggedIn)
        }
    }
}