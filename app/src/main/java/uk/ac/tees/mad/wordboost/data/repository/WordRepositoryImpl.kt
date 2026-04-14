package uk.ac.tees.mad.wordboost.data.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import uk.ac.tees.mad.wordboost.data.local.SavedWordDao
import uk.ac.tees.mad.wordboost.data.local.WordOfDayDao
import uk.ac.tees.mad.wordboost.data.model.SavedWordEntity
import uk.ac.tees.mad.wordboost.data.model.WordOfTheDayEntity
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
                    word = WordOfTheDayEntity(
                        word = result.word,
                        phonetic = result.phonetic,
                        audioUrl = result.audioUrl,
                        meaning = result.meaning,
                        example = result.example,
                        isSaved = false
                    )
                )
            }
            Log.d("fetch", "success")
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
   // called from the saved word -screen
    suspend fun getSavedWord(): Flow<List<SavedWordEntity>> = withContext(Dispatchers.IO){
       return@withContext savedWordDao.getAllWords()
    }

   //saved word screen
    suspend fun deleteWord(word: String) = withContext(Dispatchers.IO){
        savedWordDao.deleteWord(word)
       wordOfDayDao.updateWordOfDay(
           word = word,
           isSaved = false
       )
       firebaseDataSource.deleteWord(word)
    }

    //get word of the day
    suspend fun  getWordOfTheDay(): Flow<WordOfTheDayEntity?> = withContext(Dispatchers.IO){
        return@withContext wordOfDayDao.getWordOfDay()
    }

    //save word from homescreen
    suspend fun saveWord(word: WordOfTheDayEntity){
        wordOfDayDao.updateWordOfDay(word = word.word ,
            isSaved = !word.isSaved)
        val savedWord = SavedWordEntity(
            word = word.word,
            phonetic = word.phonetic,
            audioUrl = word.audioUrl,
            meaning = word.meaning,
            example = word.example
        )
        //save in saved word entity
        savedWordDao.insertWord(savedWord)
        //save at firebase
        firebaseDataSource.saveWord(savedWord)
    }

    //fetch all word from firebase at first install then store it in database
    suspend fun saveWordFromFirebaseAtFirstInstall() = withContext(Dispatchers.IO){
        val result = firebaseDataSource.fetchAllWord()
        result.forEach {
            savedWordDao.insertWord(it)
        }
    }

    suspend fun deleteAllData(){
        wordOfDayDao.deleteWordOfDay()
        savedWordDao.deleteAllWords()
    }

}





