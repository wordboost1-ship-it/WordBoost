package uk.ac.tees.mad.wordboost.preference

import android.content.Context
import androidx.core.content.edit

class AppPreference(
    context: Context
) {

    private val sharedPreferences =
        context.getSharedPreferences("wordboost_pref", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_DAILY_REMINDER = "daily_reminder"
        private const val KEY_LAST_WORD = "last_word"
        private const val KEY_LAST_FETCH_DATE = "last_fetch_date"
    }

   //reminder setup
    fun setDailyReminderEnabled(enabled: Boolean) {
        sharedPreferences.edit {
            putBoolean(KEY_DAILY_REMINDER, enabled)
        }
    }
    fun isDailyReminderEnabled(): Boolean {
        return sharedPreferences.getBoolean(KEY_DAILY_REMINDER, false)
    }

    // ---------- Last Word ----------
    fun setLastWord(word: String) {
        sharedPreferences.edit {
            putString(KEY_LAST_WORD, word)
        }
    }

    fun getLastWord(): String? {
        return sharedPreferences.getString(KEY_LAST_WORD, null)
    }

    // ---------- Last Fetch Date ----------
    fun setLastFetchDate(date: String) {
        sharedPreferences.edit {
            putString(KEY_LAST_FETCH_DATE, date)
        }
    }

    fun getLastFetchDate(): String? {
        return sharedPreferences.getString(KEY_LAST_FETCH_DATE, null)
    }
}
