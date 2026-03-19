package uk.ac.tees.mad.wordboost.ui.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.ac.tees.mad.wordboost.ui.theme.Dimens

@Composable
fun MeaningSection(
    meaning: String,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        SectionTitle(
            title = "Meaning"
        )

        Spacer(modifier = Modifier.height(Dimens.Small))

        Divider(
            color = MaterialTheme.colorScheme.outlineVariant
        )

        Spacer(modifier = Modifier.height(Dimens.Medium))

        Text(
            text = meaning,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
