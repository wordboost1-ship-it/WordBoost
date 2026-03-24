package uk.ac.tees.mad.wordboost.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import uk.ac.tees.mad.wordboost.data.model.WordResponseDto

interface WordApiService {
    @GET("api/v2/entries/en/{word}")
    suspend fun fetchWord( @Path("word")word: String): List<WordResponseDto>
}

