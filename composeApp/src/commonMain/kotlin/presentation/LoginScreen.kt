package presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import resources.Resources

class LoginScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { LoginControllerScreenModel() }
        return LoginScreenContent(screenModel)
    }
}

@Composable
internal fun LoginScreenContent(screenModel: LoginControllerScreenModel) {
    val state by screenModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        content = { _ ->
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    LaunchedEffect(Unit){
                        scope.launch {
                            screenModel.event.collectLatest {
                                snackBarHostState.showSnackbar(
                                    message = it.message,
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }
                    }

                    setupLayout(
                        state = state,
                        onEmailTextChanged = {
                            screenModel.onTextEmailChange(it)
                        },
                        onPasswordTextChanged = {
                            screenModel.onTextPasswordChange(it)
                        },
                        screenModel = screenModel
                    )
                }
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        })
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun setupLayout(
    state: LoginState,
    onEmailTextChanged: (String) -> Unit,
    onPasswordTextChanged: (String) -> Unit,
    screenModel: LoginControllerScreenModel
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val reusableModifierTextField = Modifier
        .padding(horizontal = 24.dp)
        .fillMaxWidth()

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .alpha(0.8f)
            .padding(bottom = 64.dp),
        fontSize = 64.sp,
        color = Color.Gray,
        textAlign = TextAlign.Center,
        text = Resources.String.APP_NAME
    )
    TextField(
        modifier = reusableModifierTextField,
        value = state.textEmail,
        isError = state.isErrorEmail,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        onValueChange = onEmailTextChanged,
        label = { Text(Resources.String.EMAIL) },
    )
    TextField(
        modifier = reusableModifierTextField,
        value = state.textPassword,
        isError = state.isErrorPassword,
        onValueChange = onPasswordTextChanged,
        label = { Text(Resources.String.PASSWORD) },
        visualTransformation = PasswordVisualTransformation(),
    )
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp),
        shape = CircleShape,
        onClick = {
            screenModel.onLogin()
            keyboardController?.hide()
        },
    ) {
        Text(Resources.String.LOGIN)
    }
}