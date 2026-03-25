package uk.ac.tees.mad.wordboost.notification

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import uk.ac.tees.mad.wordboost.WordBoostApp
import uk.ac.tees.mad.wordboost.data.repository.AuthRepositoryImpl
import uk.ac.tees.mad.wordboost.data.repository.WordRepositoryImpl

class DailyWordWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override suspend fun doWork(): Result {

        val wordRepository : WordRepositoryImpl =
            (applicationContext as WordBoostApp).dependencyContainer.wordRepository

        val result = wordRepository.fetchWordOfTheDay()

        if(result.isSuccess) {
            NotificationHelper.showDailyReminder(applicationContext)
            return Result.success()
        }
        return Result.failure()
    }
}
