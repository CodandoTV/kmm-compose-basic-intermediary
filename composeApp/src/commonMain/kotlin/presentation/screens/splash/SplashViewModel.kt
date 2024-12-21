package presentation.screens.splash

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.LoginRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
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

class SplashViewModel(
    dataStore: DataStore<Preferences>
) : ViewModel() {
    private val _uiState = MutableStateFlow(SlashUiState())
    val uiState: StateFlow<SlashUiState>
        get() = _uiState

    init {
        viewModelScope.launch {
            dataStore.data.first().let {
                val isLogged = booleanPreferencesKey("isLogged")
                val isLoggedValue = it[isLogged] ?: false
                _uiState.value = _uiState.value.copy(
                    appState = if (isLoggedValue) AppState.LoggedIn else AppState.NotLoggedIn
                )
            }
        }
    }
}