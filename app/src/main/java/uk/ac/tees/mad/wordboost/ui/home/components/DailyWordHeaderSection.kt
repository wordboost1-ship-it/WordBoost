package uk.ac.tees.mad.wordboost.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.wordboost.ui.theme.Dimens

@Composable
fun DailyWordHeader(
    greeting: String,
    userInitial: String,
    onProfileClick:()-> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        HeaderTextSection(
            greeting = greeting
        )

        ProfileAvatar(
            initial = userInitial,
            onProfileClick = onProfileClick
        )
    }
}



@Composable
private fun HeaderTextSection(
    greeting: String
) {

    Column {

        GreetingText(text = greeting)

        Spacer(modifier = Modifier.height(Dimens.Small))

        DailyWordTitle()
    }
}



@Composable
private fun GreetingText(
    text: String
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.SansSerif,
        color = MaterialTheme.colorScheme.primary
    )
}



@Composable
private fun DailyWordTitle() {
    Text(
        text = "Daily Word",
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.onBackground,
        fontFamily = FontFamily.SansSerif,
    )
}


@Composable
private fun ProfileAvatar(
    initial: String,
    onProfileClick:()-> Unit
) {

    Surface(
        modifier = Modifier
            .size(48.dp)
            .clickable{
                onProfileClick()
            },
        shape = CircleShape,
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 2.dp
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = initial,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

