package uk.ac.tees.mad.wordboost.ui.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.wordboost.ui.theme.Dimens

@Composable
fun ExampleSection(
    example: String,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        SectionTitle(
            title = "Example"
        )

        Spacer(modifier = Modifier.height(Dimens.Small))

        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surfaceVariant,
            tonalElevation = 1.dp,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = example,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontStyle = FontStyle.Italic
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(Dimens.Medium)
            )
        }
    }
}
