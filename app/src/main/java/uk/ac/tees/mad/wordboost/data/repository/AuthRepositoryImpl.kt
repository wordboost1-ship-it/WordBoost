package uk.ac.tees.mad.wordboost.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

const val USERS_COLLECTION = "users"

class AuthRepositoryImpl(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {

    suspend fun signIn(
        email: String,
        password: String
    ): Result<Unit> {

        return try {
            auth.signInWithEmailAndPassword(email, password)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signUp(email: String, password: String): Result<Unit> {

        return try {
            val result = auth.createUserWithEmailAndPassword(email, password)
                .await()
            val user = result.user ?: throw Exception("user creation failed")
            val userData = mapOf(
                "uid" to user.uid,
                "email" to user.email,
                "createdAt" to System.currentTimeMillis()
            )
            try {
                firestore
                    .collection(USERS_COLLECTION)
                    .document(user.uid)
                    .set(userData)
                    .await()
            } catch (e: Exception) {
                user.delete().await()
                throw e
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}