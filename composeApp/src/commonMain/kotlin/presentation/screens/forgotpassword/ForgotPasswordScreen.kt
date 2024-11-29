package presentation.screens.forgotpassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import presentation.widgets.PrimaryButton
import resources.Resources

class ForgotPasswordScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = remember { ForgotPasswordViewModel() }
        val navigator = LocalNavigator.currentOrThrow

        ForgotPasswordScreenContent(
            onForgotPassword = viewModel::onForgotPassword,
            onBackEvent = navigator::pop
        )
    }
}

@Composable
fun ForgotPasswordScreenContent(onForgotPassword: () -> Unit, onBackEvent: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = {}, actions = {
                IconButton(onClick = onBackEvent, modifier = Modifier.size(58.dp)) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            })
        },
        bottomBar = {
            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp),
                title = Resources.String.RESET_MY_PASSWORD,
                onClick = onForgotPassword,
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding(),
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(0.8f)
                    .padding(bottom = 64.dp),
                fontSize = 64.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                text = Resources.String.FORGOT_PASSWORD_MESSAGE
            )
            TextField(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(),
                value = "",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                onValueChange = { },
                label = { Text(Resources.String.EMAIL) },
            )
        }
    }
}