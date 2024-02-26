package presentation.screens.login

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.LoginRepository
import data.LoginRepositoryImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import resources.Resources

data class LoginUIState(
    val isLoading: Boolean = false,
    val textEmail: String = "",
    val textPassword: String = "",
    val isErrorEmail: Boolean = false,
    val isErrorPassword: Boolean = false
)

sealed class Event(val message: String) {
    data object Success : Event(message = Resources.String.SUCCESSFUL)
    data object Error : Event(message = Resources.String.FAILED)
}

class LoginViewModel(
    private val repository: LoginRepository = LoginRepositoryImpl()
) : ScreenModel {
    private val _state = MutableStateFlow(LoginUIState())
    val uiState: StateFlow<LoginUIState>
        get() = _state

    private val _event = MutableSharedFlow<Event>()
    val event: SharedFlow<Event>
        get() = _event

    fun onTextEmailChange(email: String) {
        _state.update {
            it.copy(
                textEmail = email,
                isErrorEmail = false
            )
        }
    }

    fun onTextPasswordChange(password: String) {
        _state.update {
            it.copy(
                textPassword = password,
                isErrorPassword = false
            )
        }
    }

    fun onLogin() {
        screenModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }

            //Just simulated long time to show loading
            delay(1000)

            val isValid = isValidEmail(uiState.value.textEmail)
                    && isValidPassword(uiState.value.textPassword)
            if (isValid) {
                repository.postLogin(uiState.value.textEmail, uiState.value.textPassword)
                _event.emit(Event.Success)
            } else {
                _event.emit(Event.Error)
            }

            _state.update {
                it.copy(isLoading = false)
            }
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