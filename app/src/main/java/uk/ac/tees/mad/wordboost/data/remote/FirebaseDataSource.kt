package uk.ac.tees.mad.wordboost.data.remote

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import uk.ac.tees.mad.wordboost.data.model.SavedWordEntity

const val USER = "users"
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
        Log.d("fetch" , "started fetching")
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