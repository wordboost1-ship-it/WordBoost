package uk.ac.tees.mad.wordboost.ui.auth.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import uk.ac.tees.mad.wordboost.ui.theme.Dimens

@Composable
fun AuthHeaderSection(
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier) {

        Text(
            text = "WordBoost",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(Dimens.Small))

        Text(
            text = "Nurture your vocabulary daily.",
            style = MaterialTheme.typography.bodyLarge,
            fontFamily = FontFamily.SansSerif,
            fontStyle = FontStyle.Italic,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )
    }
}
