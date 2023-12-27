package dev.brampie.myandroidapplication.data

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dev.brampie.myandroidapplication.data.database.AppDatabase
import dev.brampie.myandroidapplication.network.ApiService
import dev.brampie.myandroidapplication.network.NetworkConnectionInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface AppContainer {
    val appRepository: AppRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {
    private val networkCheck = NetworkConnectionInterceptor(context)

    private val client = OkHttpClient.Builder()
        .addInterceptor(networkCheck)
        .build()

    private val BASE_URL = "https://newsapi.org/v2/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json { ignoreUnknownKeys = true }
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
            apiService, AppDatabase.getDatabase(context = context).newsArticleDao(), context)
    }
}