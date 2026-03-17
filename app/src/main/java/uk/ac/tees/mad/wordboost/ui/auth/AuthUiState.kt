package uk.ac.tees.mad.wordboost.ui.auth

import uk.ac.tees.mad.wordboost.utils.AuthError
import uk.ac.tees.mad.wordboost.utils.AuthMode
import uk.ac.tees.mad.wordboost.utils.isValidEmail

data class AuthUiState(
    val authMode : AuthMode = AuthMode.SIGN_IN,
    val email : String = "",
    val password : String = "",
    val confirmPassword : String = "",
    val isLoading : Boolean = false,
    val error : AuthError ? = null,
    val navigatedToHomePage : Boolean = false
){
    val canSubmit: Boolean
        get() = when (authMode) {
            AuthMode.SIGN_IN ->
                email.isValidEmail() && password.length >= 8

            AuthMode.SIGN_UP ->
                email.isValidEmail() &&
                        password.length >= 8 &&
                        password == confirmPassword
        }
}