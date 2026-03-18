package uk.ac.tees.mad.wordboost.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uk.ac.tees.mad.wordboost.ui.auth.AuthScreen
import uk.ac.tees.mad.wordboost.ui.home.DailyWordUiState
import uk.ac.tees.mad.wordboost.ui.home.HomeScreen
import uk.ac.tees.mad.wordboost.ui.saved.SavedWordScreen
import uk.ac.tees.mad.wordboost.ui.saved.components.SavedWordCard

@Composable
fun AppNavHost(startDestination: NavRoutes ,
               navController : NavHostController
){

    NavHost(startDestination = startDestination.route,
        navController = navController) {

        composable(route = NavRoutes.AuthScreen.route){
            AuthScreen(
                onNavigateToHomeScreen = {
                    navController.navigate(NavRoutes.HomeScreen.route){
                        popUpTo(NavRoutes.AuthScreen.route){
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = NavRoutes.HomeScreen.route){

            HomeScreen(
                uiState = DailyWordUiState(
                    greeting = "good morning",
                    word = "wnnsj",
                    phonetic = "bdsbj",
                    meaning = "bdhbjw",
                    example = "bdbhwb",
                    isSaved = false,
                    isLoading = false
                ),
                onProfileClick = {

                }
            )
        }

        composable(NavRoutes.SavedScreen.route){
            SavedWordScreen()
        }
    }
}