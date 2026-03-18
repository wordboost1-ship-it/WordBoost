package uk.ac.tees.mad.wordboost.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.wordboost.ui.auth.components.AuthFormSection
import uk.ac.tees.mad.wordboost.ui.auth.components.AuthHeaderSection
import uk.ac.tees.mad.wordboost.ui.auth.components.AuthModeToggle
import uk.ac.tees.mad.wordboost.ui.auth.components.AuthPrimaryButton
import uk.ac.tees.mad.wordboost.ui.theme.Dimens


@Composable
fun AuthScreen(
    viewModel: AuthViewModel = viewModel(),
    onNavigateToHomeScreen: () -> Unit
) {
    val uiState by viewModel.authUiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.navigatedToHomePage) {
        if(uiState.navigatedToHomePage){
           onNavigateToHomeScreen()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = Dimens.ScreenHorizontalPadding)
    ) {

        AuthHeaderSection(
            modifier = Modifier.statusBarsPadding()
        )

        Spacer(modifier = Modifier.height(Dimens.ExtraLarge))

        AuthModeToggle(
            currentMode = uiState.authMode,
            onModeChange = viewModel::onAuthModeChange
        )

        Spacer(modifier = Modifier.height(Dimens.Large))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .imePadding()
                .verticalScroll(rememberScrollState())
        ) {
            AuthFormSection(
                mode = uiState.authMode,
                email = uiState.email,
                password = uiState.password,
                confirmPassword = uiState.confirmPassword,
                onEmailChange = viewModel::onEmailChange,
                onPasswordChange = viewModel::onPasswordChange,
                onConfirmPasswordChange = viewModel::onConfirmPassword,
            )

            Spacer(modifier = Modifier.height(Dimens.Large))

            AuthPrimaryButton(
                isLoading = uiState.isLoading,
                isEnabled = uiState.canSubmit,
                mode = uiState.authMode,
                onClick = viewModel::onPrimaryButtonClick
            )
        }
        Spacer(modifier = Modifier.height(Dimens.Medium))

        uiState.error?.let { it->
            AuthErrorDialog(
                error = it.error,
            )
        }
    }
}



@Composable
private fun AuthErrorDialog(error: String){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Text(
            text = error
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewAuthScreen() {
    AuthScreen(
        onNavigateToHomeScreen = {}
    )
}
