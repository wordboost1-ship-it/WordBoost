package uk.ac.tees.mad.wordboost.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import uk.ac.tees.mad.wordboost.data.model.SavedWordEntity
import uk.ac.tees.mad.wordboost.data.model.WordOfTheDayEntity

@Database(
    entities = [WordOfTheDayEntity::class,
        SavedWordEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordOfDayDao(): WordOfDayDao
    abstract fun savedWordDao(): SavedWordDao
    companion object {
        const val DATABASE_NAME = "wordboost_db"
    }
}
