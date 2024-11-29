package presentation.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedButton
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import presentation.NavigationRoutes
import presentation.widgets.PrimaryButton
import resources.Resources

@Composable
fun LoginScreen(navController: NavController) {
    val viewModel = remember { LoginViewModel() }
    val uiState by viewModel.uiState.collectAsState()

    LoginScreenContent(
        uiState = uiState,
        onEmailTextChanged = viewModel::onTextEmailChange,
        onPasswordTextChanged = viewModel::onTextPasswordChange,
        onLoginClick = viewModel::onLogin,
        onLoginResultReset = viewModel::onLoginResultReset,
        onGoToForgotPassword = {
            navController.navigate(NavigationRoutes.ForgotPassword)
        }
    )
}

@Composable
internal fun LoginScreenContent(
    uiState: LoginUIState,
    onGoToForgotPassword: () -> Unit,
    onEmailTextChanged: (String) -> Unit,
    onPasswordTextChanged: (String) -> Unit,
    onLoginClick: () -> Unit,
    onLoginResultReset: () -> Unit,
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        bottomBar = {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp)
            ) {
                PrimaryButton(
                    title = Resources.String.LOGIN,
                    onClick = {
                        onLoginClick()
                        keyboardController?.hide()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    content = {
                        Text(Resources.String.FORGOT_PASSWORD)
                    },
                    onClick = onGoToForgotPassword
                )
            }
        },
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
                    Form(
                        uiState = uiState,
                        onEmailTextChanged = onEmailTextChanged,
                        onPasswordTextChanged = onPasswordTextChanged,
                    )
                }
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    )

    LaunchedEffect(uiState.loginResult) {
        uiState.loginResult?.let { loginResult ->
            snackBarHostState.showSnackbar(
                message = loginResult.message,
                duration = SnackbarDuration.Short
            )
            onLoginResultReset()
        }
    }
}

@Composable
private fun Form(
    uiState: LoginUIState,
    onEmailTextChanged: (String) -> Unit,
    onPasswordTextChanged: (String) -> Unit,
) {
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
        value = uiState.textEmail,
        isError = uiState.isErrorEmail,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        onValueChange = onEmailTextChanged,
        label = { Text(Resources.String.EMAIL) },
    )
    TextField(
        modifier = reusableModifierTextField,
        value = uiState.textPassword,
        isError = uiState.isErrorPassword,
        onValueChange = onPasswordTextChanged,
        label = { Text(Resources.String.PASSWORD) },
        visualTransformation = PasswordVisualTransformation(),
    )
}