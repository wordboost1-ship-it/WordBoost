package uk.ac.tees.mad.wordboost.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.wordboost.ui.theme.Dimens




@Composable
fun HomeTopBar(greeting: String ,
               onSettingClick: () -> Unit ,
               onSavedClick: () -> Unit){

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Column{
            Text(
                text = greeting,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.SansSerif,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(
                modifier = Modifier.height(Dimens.Medium)
            )
            Text(
                text = "WordBoost",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground,
                fontFamily = FontFamily.SansSerif,
            )
        }
        Row{
            IconButton(onSavedClick) {
                Icon(
                    imageVector = Icons.Default.Bookmark,
                    contentDescription = "Saved",
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            IconButton(onSettingClick) {
             Icon(
                 imageVector = Icons.Default.Settings,
                 contentDescription = "Settings"
               )
            }
        }
    }
}

