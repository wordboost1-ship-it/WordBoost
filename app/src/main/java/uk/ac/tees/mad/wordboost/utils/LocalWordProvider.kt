package uk.ac.tees.mad.wordboost.utils

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.serialization.json.Json
import java.time.LocalDate

class LocalWordProvider(
    private val context: Context
) {
    private val json = Json {
        ignoreUnknownKeys = true
    }

   private fun getAllWords(): List<String> {
        val jsonString = context.assets
            .open("words.json")
            .bufferedReader()
            .use { it.readText() }

        return json.decodeFromString(jsonString)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getWordOfTheDay(): String {
        val wordList = getAllWords()
        val dayOfYear = LocalDate.now().dayOfYear

        val index = (dayOfYear - 1) % wordList.size

        return wordList[index]
    }
}
