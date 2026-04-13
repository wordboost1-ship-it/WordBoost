package uk.ac.tees.mad.wordboost.ui.saved

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.tees.mad.wordboost.WordBoostApp
import uk.ac.tees.mad.wordboost.data.repository.WordRepositoryImpl

class SavedViewModel(application: Application)
    : AndroidViewModel(application) {

    private val audioPlayer =
        (application as WordBoostApp)
            .dependencyContainer
            .player
        private val wordRepository : WordRepositoryImpl =
            (application as WordBoostApp).dependencyContainer.wordRepository

        private  val _savedUiState = MutableStateFlow(SavedUiState())
        val savedUiState = _savedUiState.asStateFlow()


    init{
        fetchSavedData()
    }


    private fun fetchSavedData() {
        viewModelScope.launch {
            _savedUiState.update {
                it.copy(isLoading = true)
            }
                 wordRepository
                .getSavedWord()
                .collect { word ->
                    word.forEach {
                        Log.d("saved", "fetchSavedData: $it")
                    }
                    _savedUiState.update {
                        it.copy(
                            isLoading = false,
                            list = word
                        )
                    }
                }
        }
    }
    fun onSpeakerClick(url : String){
        if(!url.isEmpty()){
            audioPlayer.play(url)
        }
    }

    fun onDeleteClick(word : String){
        viewModelScope.launch {
            wordRepository.deleteWord(word)
        }
    }

}