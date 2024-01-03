package dev.brampie.myandroidapplication.data

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dev.brampie.myandroidapplication.data.database.AppDatabase
import dev.brampie.myandroidapplication.network.ApiService
import dev.brampie.myandroidapplication.network.NetworkConnectionInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

interface AppContainer {
    val appRepository: AppRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {
    private val networkCheck = NetworkConnectionInterceptor(context)
    val logging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BASIC)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(networkCheck)
        .build()

    private val BASE_URL = "https://finalspaceapi.com/api/v0/"

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json
            .asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    // Lazily initializes the ApiService using Retrofit.
    private val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    // Provides a single instance of AppRepository throughout the app lifecycle.
    override val appRepository: AppRepository by lazy {
        CachingAppRepository(
            apiService,
            AppDatabase.getDatabase(context = context).characterDao(),
            AppDatabase.getDatabase(context = context).locationDao(),
            context)
    }
}