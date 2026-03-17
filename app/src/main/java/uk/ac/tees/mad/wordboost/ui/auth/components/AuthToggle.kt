package uk.ac.tees.mad.wordboost.ui.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.wordboost.utils.AuthMode

@Composable
fun AuthModeToggle(
    currentMode: AuthMode,
    onModeChange: (AuthMode) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.large
            )
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        ModeItem(
            text = AuthMode.SIGN_IN.title,
            selected = currentMode == AuthMode.SIGN_IN,
            modifier = Modifier.weight(1f)
        ) { onModeChange(AuthMode.SIGN_IN) }

        ModeItem(
            text = AuthMode.SIGN_UP.title,
            selected = currentMode == AuthMode.SIGN_UP,
            modifier = Modifier.weight(1f)
        ) { onModeChange(AuthMode.SIGN_UP) }
    }
}
