package uk.ac.tees.mad.wordboost.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.wordboost.ui.home.components.HomeTopBar
import uk.ac.tees.mad.wordboost.ui.home.components.WordCard
import uk.ac.tees.mad.wordboost.ui.theme.Dimens


@Composable
fun HomeScreen(
    onSettingClick:()-> Unit,
    onSavedClick:()-> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.homeUiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = Dimens.ScreenHorizontalPadding)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {

        HomeTopBar(
            onSettingClick = onSettingClick,
            onSavedClick = onSavedClick,
            greeting = "good morning"
        )
        Spacer(modifier = Modifier.height(Dimens.Large))

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .imePadding()
        ) {

            WordCard(
                word = uiState.word.word,
                phonetic = uiState.word.phonetic,
                meaning = uiState.word.meaning,
                example = uiState.word.example?:"no example available",
                isSaved = uiState.word.isSaved,
                onSpeakClick = viewModel::onSpeakerClick,
                onSaveClick = viewModel::onSaveClick,
                onShareClick = {}
            )
            Spacer(modifier = Modifier.height(Dimens.ExtraLarge))
        }
    }
}



@Composable
@Preview(showBackground = true)
fun HomeScreenPreview(){
    HomeScreen(

        onSettingClick = {},
        onSavedClick = {}
    )
}

