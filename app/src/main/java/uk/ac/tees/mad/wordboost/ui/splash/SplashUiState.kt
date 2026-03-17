package uk.ac.tees.mad.wordboost.ui.splash

sealed class  SplashUiState {
    object Loading : SplashUiState()
    object NavigateToAuth : SplashUiState()
    object NavigateToHome : SplashUiState()
}