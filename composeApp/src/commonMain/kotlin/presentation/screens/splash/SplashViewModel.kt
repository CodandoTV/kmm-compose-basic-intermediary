package presentation.screens.splash

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.LoginRepository
import data.LoginRepositoryImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
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

class SplashViewModel(
    dataStore: DataStore<Preferences>
) : ViewModel() {
    private val _uiState = MutableStateFlow(SplashScreenUiState())
    val uiState: StateFlow<SplashScreenUiState>
        get() = _uiState

    init {
        viewModelScope.launch {
            dataStore.data.first().let { preferences ->
                val isLogged = booleanPreferencesKey("isLogged")
                val isLoggedValue = preferences[isLogged] ?: false
                _uiState.value = _uiState.value.copy(
                    appState = if (isLoggedValue) AppState.Logged else AppState.Unlogged
                )
            }
        }
    }
}