package uk.ac.tees.mad.wordboost.navigation

sealed class NavRoutes (val route: String) {
    object AuthScreen : NavRoutes("auth_screen")
    object HomeScreen : NavRoutes("home_screen")

    object SavedScreen : NavRoutes("saved_screen")

}