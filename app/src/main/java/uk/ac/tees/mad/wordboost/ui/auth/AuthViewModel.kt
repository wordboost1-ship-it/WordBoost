package uk.ac.tees.mad.wordboost.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import uk.ac.tees.mad.wordboost.utils.AuthMode

class AuthViewModel (application: Application)
    : AndroidViewModel(application) {

        private val _authUiState = MutableStateFlow(AuthUiState())
        val authUiState = _authUiState.asStateFlow()






    //on auth mode change
    fun onAuthModeChange(authMode: AuthMode){
        _authUiState.update {
            it.copy(
                authMode = authMode
            )
        }
    }

    //on email change
    fun emailChange(email: String){
        _authUiState.update {
            it.copy(
                email = email
            )
        }
    }

    //on password change
    fun onPasswordChange(password: String){
        _authUiState.update {
            it.copy(
                password = password
            )
        }
    }

    //on confirm password change
    fun onConfirmPassword(confirmPassword: String){
        _authUiState.update {
            it.copy(
                confirmPassword = confirmPassword
            )
        }
    }

    //on signup
    fun onSignUpClick(){

    }

    //on sign in
    fun onSignInClick(){

    }

}