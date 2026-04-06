package uk.ac.tees.mad.wordboost.data.model

data class WordData(
    val word: String,
    val phonetic: String,
    val audioUrl: String,
    val meaning: String,
    val example: String?
)
