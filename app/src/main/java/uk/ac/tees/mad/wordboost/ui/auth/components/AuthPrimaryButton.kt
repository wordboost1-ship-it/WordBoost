package uk.ac.tees.mad.wordboost.ui.auth.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.wordboost.ui.theme.Dimens
import uk.ac.tees.mad.wordboost.utils.AuthMode

@Composable
fun AuthPrimaryButton(
    mode: AuthMode,
    onClick: () -> Unit,
    isEnabled: Boolean = false,
    isLoading: Boolean = false
) {

    Button(
        enabled = isEnabled && !isLoading,
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.ButtonHeight),
        shape = MaterialTheme.shapes.large
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                strokeWidth = 2.dp,
                modifier = Modifier.size(30.dp)
            )
        } else {
            Text(
                text = mode.title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }

}
