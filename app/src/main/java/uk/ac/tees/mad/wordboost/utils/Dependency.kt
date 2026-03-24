package uk.ac.tees.mad.wordboost.utils


import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import uk.ac.tees.mad.wordboost.data.local.AppDatabase
import uk.ac.tees.mad.wordboost.data.local.SavedWordDao
import uk.ac.tees.mad.wordboost.data.local.WordOfDayDao
import uk.ac.tees.mad.wordboost.data.remote.FirebaseDataSource
import uk.ac.tees.mad.wordboost.data.remote.WordApiService
import uk.ac.tees.mad.wordboost.data.repository.AuthRepositoryImpl
import uk.ac.tees.mad.wordboost.data.repository.WordRepositoryImpl
import uk.ac.tees.mad.wordboost.preference.AppPreference

class DependencyContainer(private val context : Context) {
    private val database: AppDatabase by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration() // safe for development
            .build()
    }

    val savedWordDao: SavedWordDao by lazy {
        database.savedWordDao()
    }

    val wordOfDayDao: WordOfDayDao by lazy {
        database.wordOfDayDao()
    }

    val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    val firestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    val authRepository : AuthRepositoryImpl by lazy {
        AuthRepositoryImpl(
           auth = firebaseAuth,
            firestore = firestore
        )
    }

    val firebaseDataSource by lazy {
        FirebaseDataSource(
            firestore = firestore,
            firebaseAuth = firebaseAuth
        )
    }

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        explicitNulls = false
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev/")
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .build()
    }

    val wordApiService: WordApiService by lazy {
        retrofit.create(WordApiService::class.java)
    }

    val localWordProvider : LocalWordProvider by lazy {
        LocalWordProvider(context)
    }

    val appPreference : AppPreference by lazy {
        AppPreference(context)
    }

    val wordRepository : WordRepositoryImpl by lazy {
        WordRepositoryImpl(
            apiService = wordApiService,
            localWordProvider = localWordProvider,
            wordOfDayDao = wordOfDayDao,
            savedWordDao = savedWordDao,
            firebaseDataSource = firebaseDataSource,
        )
    }
}
