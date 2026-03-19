package uk.ac.tees.mad.wordboost.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun WordActionRow(
    onSpeakClick: () -> Unit,
    onSaveClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        WordActionButton(
            icon = Icons.AutoMirrored.Filled.VolumeUp,
            contentDescription = "Speak",
            onClick = onSpeakClick
        )

        WordActionButton(
            icon = Icons.Default.Bookmark,
            contentDescription = "Save",
            onClick = onSaveClick
        )

        WordActionButton(
            icon = Icons.Default.Share,
            contentDescription = "Share",
            onClick = onShareClick
        )
    }
}
