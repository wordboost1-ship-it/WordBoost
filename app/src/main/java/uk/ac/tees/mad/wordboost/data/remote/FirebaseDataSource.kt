package uk.ac.tees.mad.wordboost.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import uk.ac.tees.mad.wordboost.data.model.SavedWordEntity

const val USER = "user"
class FirebaseDataSource (private val firestore: FirebaseFirestore,
                          private val firebaseAuth: FirebaseAuth){


    fun getUid() = firebaseAuth.currentUser?.uid

    suspend fun saveWord(word: SavedWordEntity) {
        firestore
            .collection(USER)
            .document(getUid() ?: "")
            .collection("words")
            .document(word.word)
            .set(word)
            .await()
    }


    suspend fun deleteWord(word : String){
        firestore
            .collection(USER)
            .document(getUid()?:"")
            .collection("words")
            .document(word)
            .delete()
            .await()
    }

    suspend fun fetchAllWord():List<SavedWordEntity>{
       return firestore
            .collection(USER)
            .document(getUid()?:"")
            .collection("words")
            .get()
            .await()
            .map {
                it.toObject(SavedWordEntity::class.java)
            }
    }
}