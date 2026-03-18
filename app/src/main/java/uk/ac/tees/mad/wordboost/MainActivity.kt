package uk.ac.tees.mad.wordboost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.wordboost.navigation.AppNavHost
import uk.ac.tees.mad.wordboost.navigation.NavRoutes
import uk.ac.tees.mad.wordboost.ui.splash.SplashUiState
import uk.ac.tees.mad.wordboost.ui.splash.SplashViewModel
import uk.ac.tees.mad.wordboost.ui.theme.WordBoostTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        val splashViewModel: SplashViewModel by viewModels()

        splashScreen.setKeepOnScreenCondition {
            splashViewModel.splashUiState.value is SplashUiState.Loading
        }


        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val startDestination = when(splashViewModel
                .splashUiState
                .collectAsState()
                .value) {
                SplashUiState.Loading -> null
                SplashUiState.NavigateToAuth -> NavRoutes.AuthScreen
                SplashUiState.NavigateToHome -> NavRoutes.SavedScreen
            }
            if(startDestination != null){
                WordBoostTheme {
                    AppNavHost(
                        startDestination = startDestination,
                        navController = navController
                    )
                }
            }
        }
    }
}