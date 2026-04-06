package uk.ac.tees.mad.wordboost.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("word_of_the_entity")
data class WordOfTheDayEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val word: String,
    val phonetic: String,
    val audioUrl: String,
    val meaning: String,
    val example: String?,
    val isSaved: Boolean = false
)