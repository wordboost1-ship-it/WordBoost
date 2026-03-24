package uk.ac.tees.mad.wordboost.data.model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class WordResponseDto(

    @SerialName("word")
    val word: String? = null,

    @SerialName("phonetic")
    val phonetic: String? = null,

    @SerialName("phonetics")
    val phonetics: List<PhoneticDto>? = null,

    @SerialName("meanings")
    val meanings: List<MeaningDto>? = null,

    @SerialName("license")
    val license: LicenseDto? = null,

    @SerialName("sourceUrls")
    val sourceUrls: List<String>? = null
)

@Serializable
data class PhoneticDto(

    @SerialName("text")
    val text: String? = null,

    @SerialName("audio")
    val audio: String? = null,

    @SerialName("sourceUrl")
    val sourceUrl: String? = null,

    @SerialName("license")
    val license: LicenseDto? = null
)


@Serializable
data class MeaningDto(

    @SerialName("partOfSpeech")
    val partOfSpeech: String? = null,

    @SerialName("definitions")
    val definitions: List<DefinitionDto>? = null,

    @SerialName("synonyms")
    val synonyms: List<String>? = null,

    @SerialName("antonyms")
    val antonyms: List<String>? = null
)




@Serializable
data class DefinitionDto(

    @SerialName("definition")
    val definition: String? = null,

    @SerialName("example")
    val example: String? = null,

    @SerialName("synonyms")
    val synonyms: List<String>? = null,

    @SerialName("antonyms")
    val antonyms: List<String>? = null
)



@Serializable
data class LicenseDto(

    @SerialName("name")
    val name: String? = null,

    @SerialName("url")
    val url: String? = null
)

