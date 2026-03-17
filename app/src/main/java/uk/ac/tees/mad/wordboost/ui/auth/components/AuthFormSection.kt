package uk.ac.tees.mad.wordboost.ui.auth.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.ac.tees.mad.wordboost.ui.theme.Dimens
import uk.ac.tees.mad.wordboost.utils.AuthMode

@Composable
fun AuthFormSection(
    mode: AuthMode,
    email: String,
    password: String,
    confirmPassword: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
) {

    Column {
        EmailField(email, onEmailChange)

        Spacer(modifier = Modifier.height(Dimens.Medium))

        PasswordField(password, onPasswordChange)

        if (mode == AuthMode.SIGN_UP) {
            Spacer(modifier = Modifier.height(Dimens.Medium))
            ConfirmPasswordField(confirmPassword, onConfirmPasswordChange)
        }
    }
}
