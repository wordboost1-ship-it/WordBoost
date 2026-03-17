package uk.ac.tees.mad.wordboost.ui.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import uk.ac.tees.mad.wordboost.WordBoostApp

class SplashViewModel (application: Application)
    : AndroidViewModel(application){

        val firebaseAuth : FirebaseAuth =
            (application as WordBoostApp).dependencyContainer.firebaseAuth

    private  val _splashUiState = MutableStateFlow<SplashUiState>(SplashUiState.Loading)
    val splashUiState = _splashUiState.asStateFlow()

    init {
        resolveNavigation()
    }
    fun resolveNavigation(){
        if(isUserLoggedIn()){
            _splashUiState.value = SplashUiState.NavigateToHome
        }else{
            _splashUiState.value = SplashUiState.NavigateToAuth
        }
    }
    private fun isUserLoggedIn(): Boolean = firebaseAuth.currentUser != null
}