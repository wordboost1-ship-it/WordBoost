package uk.ac.tees.mad.wordboost.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uk.ac.tees.mad.wordboost.ui.auth.AuthScreen
import uk.ac.tees.mad.wordboost.ui.home.HomeScreen
import uk.ac.tees.mad.wordboost.ui.saved.SavedWordScreen
import uk.ac.tees.mad.wordboost.ui.setting.SettingScreen

@RequiresApi(Build.VERSION_CODES.O)
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
                onSettingClick = {
                    navController.navigate(NavRoutes.SettingScreen.route)
                },
                onSavedClick = {
                    navController.navigate(NavRoutes.SavedScreen.route)
                }
            )
        }

        composable(NavRoutes.SavedScreen.route){
            SavedWordScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            NavRoutes.SettingScreen.route
        ){
            SettingScreen(
                onBackCLick = {
                    navController.popBackStack()
                },
                onLogoutClick = {
                    navController.navigate(NavRoutes.AuthScreen.route){
                        popUpTo(NavRoutes.SettingScreen.route){
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}