package dev.brampie.myandroidapplication.data

import android.util.Log
import androidx.work.Constraints
import androidx.work.NetworkType
import dev.brampie.myandroidapplication.data.database.NewsarticleDao
import dev.brampie.myandroidapplication.data.database.asDbNewsarticle
import dev.brampie.myandroidapplication.data.database.asDomainNewsarticles
import dev.brampie.myandroidapplication.model.newsarticle.Newsarticle
import dev.brampie.myandroidapplication.network.ApiService
import dev.brampie.myandroidapplication.network.asDomainObjects
import dev.brampie.myandroidapplication.network.getNewsAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.net.SocketTimeoutException

interface AppRepository {
    fun getNewsArticles(): Flow<List<Newsarticle>>

    suspend fun insertNewsarticle(newsarticle: Newsarticle)

    suspend fun refresh()
}

class CachingAppRepository(
    private val apiService: ApiService,
    private val newsArticleDao: NewsarticleDao
) : AppRepository {
    override fun getNewsArticles(): Flow<List<Newsarticle>> {
        return newsArticleDao.getAllNewsarticles().map {
            it.asDomainNewsarticles()
        }.onEach {
            if (it.isEmpty()) {
                refresh()
            }
        }
    }

    override suspend fun insertNewsarticle(newsarticle: Newsarticle) {
        newsArticleDao.insertNewsarticle(newsarticle.asDbNewsarticle())
    }

    override suspend fun refresh() {
        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

        try {
            apiService.getNewsAsFlow().asDomainObjects().collect {
                    value ->
                for (newsarticle in value) {
                    Log.i("TEST", "refresh: $value")
                    insertNewsarticle(newsarticle)
                }
            }
        } catch (e: SocketTimeoutException) {
            // log something
        }
    }
}