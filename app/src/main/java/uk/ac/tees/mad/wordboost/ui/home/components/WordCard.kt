package uk.ac.tees.mad.wordboost.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.wordboost.ui.theme.Dimens

@Composable
fun WordCard(
    word: String,
    phonetic: String,
    meaning: String,
    example: String,
    isSaved: Boolean,
    onSpeakClick: () -> Unit,
    onSaveClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {

        Surface(
            shape = MaterialTheme.shapes.large,
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(Dimens.CardPadding)
            ) {

                WordTopSection(
                    word = word,
                    phonetic = phonetic
                )

                Spacer(modifier = Modifier.height(Dimens.Large))

                WordActionRow(
                    onSpeakClick = onSpeakClick,
                    onSaveClick = onSaveClick,
                    onShareClick = onShareClick
                )

                Spacer(modifier = Modifier.height(Dimens.Large))

                MeaningSection(
                    meaning = meaning
                )

                Spacer(modifier = Modifier.height(Dimens.Large))

                ExampleSection(
                    example = example
                )
            }
        }

        if (isSaved) {
            SaveSuccessOverlay()
        }
    }
}


@Composable
private fun WordTopSection(
    word: String,
    phonetic: String
) {

    Column(modifier = Modifier.fillMaxWidth()) {

        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center){
            Text(
                text = word,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
            )
        }

        Spacer(modifier = Modifier.height(Dimens.Small))

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            Text(
                text = phonetic,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            )
        }
    }
}
