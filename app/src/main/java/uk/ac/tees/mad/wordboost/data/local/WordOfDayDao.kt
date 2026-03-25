package uk.ac.tees.mad.wordboost.data.local

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uk.ac.tees.mad.wordboost.data.model.WordOfThedayEntity

interface WordOfDayDao{
    @Query("SELECT * FROM word_of_the_entity")
    suspend fun getWordOfDay():List<WordOfThedayEntity>

    @Query("UPDATE word_of_the_entity SET isSaved = :isSaved WHERE word = :word")
    suspend fun updateWordOfDay(word: String, isSaved: Boolean)

    @Query("DELETE FROM word_of_the_entity")
    suspend fun deleteWordOfDay()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordOfDay(word: WordOfThedayEntity)

}