package uk.ac.tees.mad.wordboost.ui.saved.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.wordboost.ui.theme.Dimens

@Composable
fun SavedWordCard(
    word: String,
    phonetic: String,
    meaningPreview: String,
    onSpeakClick: () -> Unit,
    onDeleteClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 1.dp,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {

        Row(
            modifier = Modifier
                .padding(Dimens.CardPadding)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                WordTitleRow(
                    word = word,
                    phonetic = phonetic
                )

                Spacer(modifier = Modifier.height(Dimens.Small))

                MeaningPreviewText(
                    text = meaningPreview
                )
            }

            Spacer(modifier = Modifier.width(Dimens.Medium))

            ActionColumn(
                onSpeakClick = onSpeakClick,
                onDeleteClick = {
                    onDeleteClick(word)
                }
            )
        }
    }
}




@Composable
private fun WordTitleRow(
    word: String,
    phonetic: String
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = word,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.width(Dimens.Small))

        PhoneticBadge(phonetic = phonetic)
    }
}



@Composable
private fun PhoneticBadge(
    phonetic: String
) {

    Surface(
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Text(
            text = phonetic,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(
                horizontal = Dimens.Small,
                vertical = Dimens.ExtraSmall
            )
        )
    }
}


@Composable
private fun MeaningPreviewText(
    text: String
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}



@Composable
private fun ActionColumn(
    onSpeakClick: () -> Unit,
    onDeleteClick: () -> Unit
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.Small),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        IconButton(onClick = {onSpeakClick()}) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                contentDescription = "Speak",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        IconButton(onClick = onDeleteClick) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
}


