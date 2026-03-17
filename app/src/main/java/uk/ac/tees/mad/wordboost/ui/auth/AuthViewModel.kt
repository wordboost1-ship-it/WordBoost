package uk.ac.tees.mad.wordboost.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.tees.mad.wordboost.WordBoostApp
import uk.ac.tees.mad.wordboost.data.repository.AuthRepositoryImpl
import uk.ac.tees.mad.wordboost.utils.AuthErrorMapper
import uk.ac.tees.mad.wordboost.utils.AuthMode

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val authRepository: AuthRepositoryImpl =
        (application as WordBoostApp).dependencyContainer.authRepository
    private val _authUiState = MutableStateFlow(AuthUiState())
    val authUiState = _authUiState.asStateFlow()

    //on auth mode change
    fun onAuthModeChange(authMode: AuthMode) {
        _authUiState.update {
            it.copy(
                email = "",
                password = "",
                confirmPassword = "",
                authMode = authMode
            )
        }

    }

    //on email change
    fun onEmailChange(email: String) {
        _authUiState.update {
            it.copy(
                email = email
            )
        }
    }

    //on password change
    fun onPasswordChange(password: String) {
        _authUiState.update {
            it.copy(
                password = password
            )
        }
    }

    //on confirm password change
    fun onConfirmPassword(confirmPassword: String) {
        _authUiState.update {
            it.copy(
                confirmPassword = confirmPassword
            )
        }
    }


    fun onPrimaryButtonClick() {
        val state = _authUiState.value
        when (state.authMode) {
            AuthMode.SIGN_IN -> signUp()
            AuthMode.SIGN_UP -> signIn()
        }
    }

    //on signup
    private fun signUp() {
        viewModelScope.launch {
            _authUiState.update {
                it.copy(
                    isLoading = true
                )
            }

            val state = _authUiState.value
            authRepository.signUp(
                state.email,
                state.password
            )
                .onFailure { error ->
                    _authUiState.update {
                        it.copy(
                            isLoading = false,
                            error = AuthErrorMapper.map(Exception(error))
                        )
                    }
                }
                .onSuccess {
                    _authUiState.update {
                        it.copy(
                            isLoading = false,
                            error = null,
                            navigatedToHomePage = true
                        )
                    }
                }
        }
    }

    //on sign in
    private fun signIn() {
        viewModelScope.launch {
            _authUiState.update {
                it.copy(
                    isLoading = true
                )
            }
            val state = _authUiState.value
            authRepository.signIn(
                state.email,
                state.password
            )
                .onSuccess {
                    _authUiState.update {
                        it.copy(
                            isLoading = false,
                            error = null,
                            navigatedToHomePage = true
                        )
                    }
                }.onFailure { error ->
                    _authUiState.update {
                        it.copy(
                            isLoading = false,
                            error = AuthErrorMapper.map(Exception(error))
                        )
                    }
                }
        }
    }

}