package uk.ac.tees.mad.wordboost

import android.app.Application
import uk.ac.tees.mad.wordboost.utils.DependencyContainer

class WordBoostApp : Application() {
  lateinit var dependencyContainer: DependencyContainer
    private set
  override fun onCreate() {
    super.onCreate()
    dependencyContainer = DependencyContainer()
  }
}