package uk.ac.tees.mad.wordboost.ui.setting

data class SettingUiState(
    val isReminderEnabled: Boolean = false,
    val name :String = "",
    val email :String ="",
    val firstCharOfName : String = "",
)