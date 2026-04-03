package uk.ac.tees.mad.wordboost.ui.saved

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.wordboost.ui.saved.components.SavedWordCard
import uk.ac.tees.mad.wordboost.ui.saved.components.SavedWordTopBar
import uk.ac.tees.mad.wordboost.ui.theme.Dimens

@Composable
fun SavedWordScreen(
    onBackClick:()->Unit,
    viewModel: SavedViewModel = viewModel()
){
    val uiState by viewModel.savedUiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(Dimens.ScreenHorizontalPadding)
            .statusBarsPadding()
            .navigationBarsPadding(),
    ) {
        SavedWordTopBar(
            modifier = Modifier,
            onBackClick = onBackClick
        )

        Spacer(
            modifier = Modifier.height(Dimens.Large)
        )

        LazyColumn (
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(Dimens.Small)

        ) {
            items(uiState.list) { it ->
                SavedWordCard(
                    word = it.word,
                    phonetic = it.phonetic,
                    meaningPreview = it.meaning,
                    onSpeakClick = { viewModel.onSpeakerClick(it.audioUrl) },
                    onDeleteClick = viewModel::onDeleteClick
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun SavedWordScreenPreview(){
    SavedWordScreen(onBackClick = {})
}