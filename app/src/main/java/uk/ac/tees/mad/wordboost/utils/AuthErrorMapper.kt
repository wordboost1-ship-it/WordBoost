package uk.ac.tees.mad.wordboost.utils


import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException

object AuthErrorMapper {

    fun map(exception: Exception): AuthError {

        return when (exception) {

            is FirebaseAuthInvalidUserException ->
                AuthError.UserNotFound

            is FirebaseAuthUserCollisionException ->
                AuthError.EmailAlreadyInUse

            is FirebaseNetworkException ->
                AuthError.NetworkError

            else ->
                AuthError.Unknown
        }
    }
}
