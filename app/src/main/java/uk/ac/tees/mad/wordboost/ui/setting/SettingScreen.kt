package uk.ac.tees.mad.wordboost.ui.setting

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.wordboost.ui.setting.components.DailyReminder
import uk.ac.tees.mad.wordboost.ui.setting.components.SettingTopBar
import uk.ac.tees.mad.wordboost.ui.setting.components.SignOutButton
import uk.ac.tees.mad.wordboost.ui.setting.components.UserProfileCard
import uk.ac.tees.mad.wordboost.ui.theme.Dimens


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SettingScreen(onBackCLick:()-> Unit ,
                  onLogoutClick:()-> Unit,
                  viewModel : SettingViewModel = viewModel()) {

    val uiState by viewModel.settingUiState.collectAsStateWithLifecycle()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(Dimens.ScreenHorizontalPadding)
                .statusBarsPadding()
                .navigationBarsPadding()
        ) {
            SettingTopBar(
                onBackClick = onBackCLick
            )
            Spacer(
                modifier = Modifier.height(Dimens.Small)
            )
            UserProfileCard(
                name = uiState.name,
                email = uiState.email,
                firstChar = uiState.firstCharOfName
            )
            Spacer(
                modifier = Modifier.height(Dimens.Small)
            )
            DailyReminder(
                isEnabled = uiState.isReminderEnabled,
                onToggle = viewModel::onReminderToggle
            )
            Spacer(
                modifier = Modifier.weight(1f)
            )
            SignOutButton(
                onClick = {
                    viewModel.onSignOutClick{
                        onLogoutClick()
                    }
                }
            )
        }
    }


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = true)
fun SettingScreenPreview(){
    SettingScreen({},
        {})
}

