package uk.ac.tees.mad.wordboost.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uk.ac.tees.mad.wordboost.ui.auth.AuthScreen

@Composable
fun AppNavHost(startDestination: NavRoutes ,
               navController : NavHostController
){

    NavHost(startDestination = startDestination.route,
        navController = navController) {

        composable(route = NavRoutes.AuthScreen.route){
            AuthScreen(
                onNavigateToHomeScreen = {
                    navController.navigate(NavRoutes.HomeScreen.route)
                }
            )
        }

        composable(route = NavRoutes.HomeScreen.route){

        }
    }
}