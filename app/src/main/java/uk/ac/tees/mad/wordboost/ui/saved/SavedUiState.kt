package uk.ac.tees.mad.wordboost.ui.saved

import uk.ac.tees.mad.wordboost.data.model.SavedWordEntity

data class SavedUiState(
    val isLoading: Boolean = false,
    val list: List<SavedWordEntity> = emptyList(),
)