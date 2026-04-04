package uk.ac.tees.mad.wordboost.notification

import android.app.Application
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.time.Duration
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

class ReminderScheduler(
   private val context : Context
) {

    private val workManager = WorkManager.getInstance(context)

    private val WORK_NAME = "daily_word_reminder"

    @RequiresApi(Build.VERSION_CODES.O)
    fun enable() {
        schedule()
    }

    fun disable() {
        Log.d("ReminderScheduler", "disabled")
        workManager.cancelUniqueWork(WORK_NAME)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun schedule() {

        val initialDelay = calculateInitialDelay()

        val request = PeriodicWorkRequestBuilder<DailyWordWorker>(
            1, TimeUnit.DAYS
        )
            .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
            .build()
        Log.d("ReminderScheduler", "schedule: $request")
        workManager.enqueueUniquePeriodicWork(
            WORK_NAME,
            ExistingPeriodicWorkPolicy.UPDATE,
            request
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculateInitialDelay(): Long {

        val now = LocalDateTime.now()

        var next6AM = now
            .withHour(6)
            .withMinute(0)
            .withSecond(0)

        if (now >= next6AM) {
            next6AM = next6AM.plusDays(1)
        }

        return Duration.between(now, next6AM).toMillis()
    }
}
