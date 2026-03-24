package uk.ac.tees.mad.wordboost.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uk.ac.tees.mad.wordboost.data.local.SavedWordDao
import uk.ac.tees.mad.wordboost.data.local.WordOfDayDao
import uk.ac.tees.mad.wordboost.data.model.SavedWordEntity
import uk.ac.tees.mad.wordboost.data.model.WordOfThedayEntity
import uk.ac.tees.mad.wordboost.data.remote.FirebaseDataSource
import uk.ac.tees.mad.wordboost.data.remote.WordApiService
import uk.ac.tees.mad.wordboost.utils.LocalWordProvider
import uk.ac.tees.mad.wordboost.utils.toWordData

class WordRepositoryImpl (private val apiService: WordApiService,
                          private val localWordProvider: LocalWordProvider,
                          private val wordOfDayDao: WordOfDayDao,
                          private val savedWordDao: SavedWordDao,
                          private val firebaseDataSource: FirebaseDataSource){

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun fetchWordOfTheDay() : Result<Unit>  = withContext(Dispatchers.IO){
        return@withContext try {
            val word = localWordProvider.getWordOfTheDay()
            val result = apiService
                .fetchWord(word)
                .toWordData()

            result?.let {
                wordOfDayDao.deleteWordOfDay()
                wordOfDayDao.insertWordOfDay(
                    word = WordOfThedayEntity(
                        word = result.word,
                        phonetic = result.phonetic,
                        audioUrl = result.audioUrl,
                        meaning = result.meaning,
                        example = result.example,
                        isSaved = false
                    )
                )
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
   // called from the saved word -screen
    suspend fun getSavedWord():List<SavedWordEntity> = withContext(Dispatchers.IO){
        return@withContext savedWordDao.getAllWords()
    }
   //saved word screen
    suspend fun deleteWord(word: String) = withContext(Dispatchers.IO){
        savedWordDao.deleteWord(word)
    }
    //fetch all word from firebase at first install then store it in database
    suspend fun saveWordFromFirebaseAtFirstInstall() = withContext(Dispatchers.IO){
        val result = firebaseDataSource.fetchAllWord()
        result.forEach {
            savedWordDao.insertWord(it)
        }
    }
}