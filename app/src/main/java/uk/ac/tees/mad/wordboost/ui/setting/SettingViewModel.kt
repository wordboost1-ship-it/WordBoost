package uk.ac.tees.mad.wordboost.ui.setting

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.tees.mad.wordboost.WordBoostApp
import uk.ac.tees.mad.wordboost.data.repository.WordRepositoryImpl
import uk.ac.tees.mad.wordboost.notification.ReminderScheduler
import uk.ac.tees.mad.wordboost.preference.AppPreference

class SettingViewModel (application: Application)
    : AndroidViewModel(application){
    private val reminderScheduler : ReminderScheduler =
        (application as WordBoostApp).dependencyContainer.reminderScheduler

    private val firebaseAuth : FirebaseAuth =
        (application as WordBoostApp).dependencyContainer.firebaseAuth

    private val appPreference : AppPreference =
        (application as WordBoostApp).dependencyContainer.appPreference

    private  val wordRepository : WordRepositoryImpl =
        (application as WordBoostApp).dependencyContainer.wordRepository


    private val _settingUiState = MutableStateFlow(SettingUiState())
    val settingUiState = _settingUiState.asStateFlow()



    init {
        resolveInitialSettingUiState()
    }


    private  fun resolveInitialSettingUiState(){
        val isReminderEnabled = appPreference.isDailyReminderEnabled()
        val email = firebaseAuth.currentUser?.email ?:"abc@gmail.com"
        val userName = firebaseAuth.currentUser?.displayName?:"User"
        val firstCharOfName = userName.firstOrNull()?.toString()?:"U"

        _settingUiState.update {
            it.copy(
                isReminderEnabled = isReminderEnabled,
                email = email,
                name = userName,
                firstCharOfName = firstCharOfName
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onReminderToggle(enable : Boolean){
        appPreference.setDailyReminderEnabled(enable)
        _settingUiState.update {
            it.copy(
                isReminderEnabled = enable,
            )
        }
        //schedule--->>>reminder
        if(enable){
            reminderScheduler.enable()
        }else{
            reminderScheduler.disable()
        }
    }

    fun onSignOutClick(onSuccess:()-> Unit) {
        viewModelScope.launch {
            try {
                firebaseAuth.signOut()
                reminderScheduler.disable()
                appPreference.setDailyReminderEnabled(false)
                wordRepository.deleteAllData()
                onSuccess()
            } catch (e: Exception) {
            }
        }
    }
}