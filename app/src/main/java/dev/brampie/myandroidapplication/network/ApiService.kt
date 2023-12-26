package dev.brampie.myandroidapplication.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET

interface ApiService{
    @GET("news")
    suspend fun getNews(): ApiNewsarticleResponse
}

fun ApiService.getNewsAsFlow() : Flow<ApiNewsarticleResponse> = flow {
    try{
        emit(getNews())
    }catch (e: Exception){
        e.printStackTrace()
    }
}