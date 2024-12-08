package presentation.screens.login

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
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

data class LoginUIState(
    val isLoading: Boolean = false,
    val textEmail: String = "",
    val textPassword: String = "",
    val isErrorEmail: Boolean = false,
    val isErrorPassword: Boolean = false,
    val loginResult: LoginResult? = null,
    val goToHome: Boolean = false
)

sealed class LoginResult(val message: String) {
    data object Success : LoginResult(message = Resources.String.SUCCESSFUL)
    data object Error : LoginResult(message = Resources.String.FAILED)
}

class LoginViewModel(
    private val repository: LoginRepository = LoginRepositoryImpl(),
    private val dataStore: DataStore<Preferences>
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState: StateFlow<LoginUIState>
        get() = _uiState

    fun onTextEmailChange(email: String) {
        val isEmailValid = isValidEmail(email)
        _uiState.update {
            it.copy(
                textEmail = email,
                isErrorEmail = isEmailValid.not()
            )
        }
    }

    fun onTextPasswordChange(password: String) {
        _uiState.update {
            it.copy(
                textPassword = password,
                isErrorPassword = false
            )
        }
    }

    fun onLogin() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }

            //Just simulated long time to show loading
            delay(1000)

            val isValid = isValidEmail(uiState.value.textEmail)
                    && isValidPassword(uiState.value.textPassword)

            val loginResult = if (isValid) {
//                repository.postLogin(uiState.value.textEmail, uiState.value.textPassword)
                LoginResult.Success
            } else {
                LoginResult.Error
            }

            _uiState.update {
                it.copy(
                    isLoading = false,
                    loginResult = loginResult
                )
            }

            if (loginResult is LoginResult.Success) {
                val isLogged = booleanPreferencesKey("isLogged")
                viewModelScope.launch {
                    dataStore.edit { preferences ->
                        preferences[isLogged] = true
                    }
                }
                _uiState.update {
                    it.copy(goToHome = true)
                }
            }
        }
    }

    fun onLoginResultReset() {
        _uiState.update {
            it.copy(
                loginResult = null
            )
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
        return email.matches(emailRegex)
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length > 3
    }
}