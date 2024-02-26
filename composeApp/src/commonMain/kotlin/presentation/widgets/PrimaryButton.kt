package presentation.widgets

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PrimaryButton(title: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        modifier = modifier,
        shape = CircleShape,
        onClick = onClick,
    ) {
        Text(
            text = title
        )
    }
}