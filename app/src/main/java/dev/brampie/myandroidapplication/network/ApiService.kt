package dev.brampie.myandroidapplication.network

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{
    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): ApiNewsarticleResponse

}

fun ApiService.getNewsAsFlow() : Flow<ApiNewsarticleResponse> = flow {
    try{
        emit(getNews("us", "e308318878754736857cd90a835245a0"))
    }catch (e: Exception){
        Log.e("API", "getTasksAsFlow: "+e.stackTraceToString(), )
        e.printStackTrace()
    }
}