package uk.ac.tees.mad.wordboost.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import uk.ac.tees.mad.wordboost.ui.home.components.DailyWordHeader
import uk.ac.tees.mad.wordboost.ui.home.components.WordCard
import uk.ac.tees.mad.wordboost.ui.theme.Dimens


@Composable
fun HomeScreen(
    uiState: DailyWordUiState,
    onProfileClick:()-> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = Dimens.ScreenHorizontalPadding)
    ) {

        DailyWordHeader(
            greeting = uiState.greeting,
            userInitial = "A",
            modifier = Modifier
                .statusBarsPadding(),
            onProfileClick = onProfileClick
        )

        Spacer(modifier = Modifier.height(Dimens.Large))

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .imePadding()
        ) {

            WordCard(
                word = uiState.word,
                phonetic = uiState.phonetic,
                meaning = uiState.meaning,
                example = uiState.example,
                isSaved = uiState.isSaved,
                onSpeakClick = {},
                onSaveClick = {},
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
        uiState = DailyWordUiState(
            greeting = "good morning",
            word = "wnnsj",
            phonetic = "bdsbj",
            meaning = "bdhbjw",
            example = "bdbhwb",
            isSaved = false,
            isLoading = false,
        ),
        onProfileClick = {}
    )
}



data class DailyWordUiState(
    val greeting: String,
    val word: String,
    val phonetic: String,
    val meaning: String,
    val example: String,
    val isSaved: Boolean,
    val isLoading: Boolean
)
