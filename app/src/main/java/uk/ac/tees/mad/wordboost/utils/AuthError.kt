package uk.ac.tees.mad.wordboost.utils

sealed class AuthError(val error: String) {
    data object UserNotFound : AuthError("user not found")
    data object WrongPassword : AuthError("wrong password")
    data object EmailAlreadyInUse : AuthError("email already in use")
    data object NetworkError : AuthError("network error")
    data object Unknown : AuthError("unknown error")
}
