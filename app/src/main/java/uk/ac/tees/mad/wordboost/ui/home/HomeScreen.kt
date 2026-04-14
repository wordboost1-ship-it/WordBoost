package uk.ac.tees.mad.wordboost.ui.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
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

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Permission granted ✅
        } else {
            // Permission denied ❌
        }
    }

    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            when {
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED -> {
                    // Already granted ✅
                }

                else -> {
                    // Request permission
                    launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
    }

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
                onShareClick = {
                    val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, uiState.word.word)
                        type = "text/plain"
                        putExtra(Intent.EXTRA_SUBJECT, "Word of the day")
                    }
                    val shareIntent = Intent.createChooser(sendIntent, null)
                    context.startActivity(shareIntent)
                }
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

