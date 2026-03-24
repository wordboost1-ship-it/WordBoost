package uk.ac.tees.mad.wordboost.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uk.ac.tees.mad.wordboost.data.model.SavedWordEntity

@Dao
interface SavedWordDao{
    @Query("SELECT * FROM saved_words")
    suspend fun getAllWords(): List<SavedWordEntity>

    @Query("Delete FROM saved_words WHERE word = :word")
    suspend fun deleteWord(word: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWord(word: SavedWordEntity)
}
