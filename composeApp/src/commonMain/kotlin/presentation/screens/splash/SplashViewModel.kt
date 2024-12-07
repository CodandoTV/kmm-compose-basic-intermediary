package presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.LoginRepository
import data.LoginRepositoryImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import resources.Resources

data class SplashScreenUiState(
    val appState: AppState = AppState.Loading
)

sealed class AppState {
    data object Loading : AppState()
    data object Logged : AppState()
    data object Unlogged : AppState()
}

class SplashViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SplashScreenUiState())
    val uiState: StateFlow<SplashScreenUiState>
        get() = _uiState

    init {
        viewModelScope.launch {
            delay(1000)
            _uiState.value = _uiState.value.copy(
                appState = AppState.Unlogged
            )
        }
    }
}