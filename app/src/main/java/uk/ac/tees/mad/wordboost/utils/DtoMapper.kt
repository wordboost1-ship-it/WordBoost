package uk.ac.tees.mad.wordboost.utils

import uk.ac.tees.mad.wordboost.data.model.WordData
import uk.ac.tees.mad.wordboost.data.model.WordResponseDto



fun List<WordResponseDto>.toWordData(): WordData? {

    if (this.isEmpty()) return null

    val entry = this.first()

    val word = entry.word.orEmpty()

    val phoneticText = when {
        !entry.phonetic.isNullOrBlank() -> entry.phonetic
        else -> entry.phonetics
            ?.firstOrNull { !it.text.isNullOrBlank() }
            ?.text
    }.orEmpty()

    val audioUrl = entry.phonetics
        ?.firstOrNull { !it.audio.isNullOrBlank() }
        ?.audio
        .orEmpty()

    val firstDefinition = entry.meanings
        ?.asSequence()
        ?.flatMap { it.definitions.orEmpty() }
        ?.firstOrNull { !it.definition.isNullOrBlank() }

    val meaningText = firstDefinition?.definition.orEmpty()

    val exampleText = entry.meanings
        ?.asSequence()
        ?.flatMap { it.definitions.orEmpty() }
        ?.firstOrNull { !it.example.isNullOrBlank() }
        ?.example

    return WordData(
        word = word,
        phonetic = phoneticText,
        audioUrl = audioUrl,
        meaning = meaningText,
        example = exampleText
    )
}
