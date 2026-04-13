package uk.ac.tees.mad.wordboost.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("saved_words")
data class SavedWordEntity(
//    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @PrimaryKey  val word: String = "",
    val phonetic: String = "",
    val audioUrl: String = "",
    val meaning: String = "",
    val example: String? = null,
)