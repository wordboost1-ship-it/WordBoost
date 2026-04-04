package uk.ac.tees.mad.wordboost.ui.home

import uk.ac.tees.mad.wordboost.data.model.WordOfTheDayEntity

data class HomeUiState(
    val isLoading : Boolean = false,
    val word : WordOfTheDayEntity = dummyWord
)

val dummyWord = WordOfTheDayEntity(
    id = 0,
    word = "love",
    phonetic = "/lʊv/",
    audioUrl = "https://api.dictionaryapi.dev/media/pronunciations/en/love-uk.mp3",
    meaning = "Strong affection",
    example = "Hello love, how can I help you?",
    isSaved = false
)