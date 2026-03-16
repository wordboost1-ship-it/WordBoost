package uk.ac.tees.mad.wordboost.utils

import android.util.Patterns

fun String.isValidEmail(): Boolean {
    return isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}
