package uk.ac.tees.mad.wordboost.ui.home

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.tees.mad.wordboost.WordBoostApp
import uk.ac.tees.mad.wordboost.data.repository.WordRepositoryImpl
import uk.ac.tees.mad.wordboost.notification.ReminderScheduler
import uk.ac.tees.mad.wordboost.preference.AppPreference
import uk.ac.tees.mad.wordboost.ui.setting.SettingUiState
import uk.ac.tees.mad.wordboost.utils.AudioPlayer

class HomeViewModel (application: Application)
    : AndroidViewModel(application) {
        private  val wordRepository : WordRepositoryImpl =
            (application as WordBoostApp).dependencyContainer.wordRepository

       private val audioPlayer: AudioPlayer =
           (application as WordBoostApp).dependencyContainer.player
        private val _homeUiState = MutableStateFlow(HomeUiState())
        val homeUiState = _homeUiState.asStateFlow()

    init {
        fetchWordOfTheDay()
    }


    private fun fetchWordOfTheDay(){
        viewModelScope.launch {
            _homeUiState.update {
                it.copy(
                    isLoading = true
                )
            }
                 wordRepository
                .getWordOfTheDay()
                .collect { entity->
                    _homeUiState.update {
                        it.copy(
                            isLoading = false,
                            word = entity?:dummyWord
                        )
                    }
                }
        }
    }


    fun onSaveClick(){
        viewModelScope.launch {
            _homeUiState.update {
                it.copy(isLoading = true)
            }
            val word = _homeUiState.value.word
            if(!word.isSaved){
                wordRepository.saveWord(word)
            }
            _homeUiState.update {
                it.copy(
                    isLoading = false,
                )
            }
        }
    }

    fun onSpeakerClick(){
        audioPlayer.play(_homeUiState.value.word.audioUrl)
    }
}