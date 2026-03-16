package uk.ac.tees.mad.wordboost.utils


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import uk.ac.tees.mad.wordboost.data.repository.AuthRepositoryImpl

class DependencyContainer {
    val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    val firestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    val authRepository : AuthRepositoryImpl by lazy {
        AuthRepositoryImpl(
           auth = firebaseAuth,
            firestore = firestore
        )
    }

}
