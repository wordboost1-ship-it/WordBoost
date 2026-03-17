package uk.ac.tees.mad.wordboost.ui.auth.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.ac.tees.mad.wordboost.ui.theme.Dimens

@Composable
fun EmailField(
    value: String,
    onValueChange: (String) -> Unit
) {

    Column {

        Text(
            text = "Email Address",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(Dimens.Small))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    "you@example.com",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            shape = MaterialTheme.shapes.medium
        )
    }
}
