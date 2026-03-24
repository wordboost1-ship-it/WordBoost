package uk.ac.tees.mad.wordboost.ui.setting

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import uk.ac.tees.mad.wordboost.ui.setting.components.DailyReminder
import uk.ac.tees.mad.wordboost.ui.setting.components.SettingTopBar
import uk.ac.tees.mad.wordboost.ui.setting.components.SignOutButton
import uk.ac.tees.mad.wordboost.ui.setting.components.UserProfileCard
import uk.ac.tees.mad.wordboost.ui.theme.Dimens

@Composable
fun SettingScreen(onBackCLick:()-> Unit) {
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
                name = "arman",
                email = "abc@gmail.com"
            )
            Spacer(
                modifier = Modifier.height(Dimens.Small)
            )
            DailyReminder(
                isEnabled = true,
                onToggle = {}
            )
            Spacer(
                modifier = Modifier.weight(1f)
            )
            SignOutButton(
                onClick = {}
            )
        }
    }


@Composable
@Preview(showBackground = true)
fun SettingScreenPreview(){
    SettingScreen({})
}

