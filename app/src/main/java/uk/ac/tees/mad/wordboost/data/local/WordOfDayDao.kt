package uk.ac.tees.mad.wordboost.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.wordboost.data.model.WordOfTheDayEntity

@Dao
interface WordOfDayDao{
    @Query("SELECT * FROM word_of_the_entity")
     fun getWordOfDay(): Flow<WordOfTheDayEntity?>

    @Query("UPDATE word_of_the_entity SET isSaved = :isSaved WHERE word = :word")
    suspend fun updateWordOfDay(word: String, isSaved: Boolean)

    @Query("DELETE FROM word_of_the_entity")
    suspend fun deleteWordOfDay()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordOfDay(word: WordOfTheDayEntity)

}