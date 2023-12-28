package dev.brampie.myandroidapplication.data

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
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
import java.util.UUID

interface AppRepository {
    fun getNewsarticles(): Flow<List<Newsarticle>>

    suspend fun insertNewsarticle(newsarticle: Newsarticle)

    suspend fun refresh()

    var wifiWorkInfo: Flow<WorkInfo>
}

class CachingAppRepository(
    private val apiService: ApiService,
    private val newsArticleDao: NewsarticleDao,
    context: Context
) : AppRepository {
    override fun getNewsarticles(): Flow<List<Newsarticle>> {
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

    private var workID = UUID(1,2)
    //the manager is private to the repository
    private val workManager = WorkManager.getInstance(context)
    //the info function is public
    override var wifiWorkInfo: Flow<WorkInfo> =
        workManager.getWorkInfoByIdFlow(workID)

    override suspend fun refresh() {
        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

//        val requestBuilder = OneTimeWorkRequestBuilder<WifiNotificationWorker>()
//        val request = requestBuilder.setConstraints(constraints).build()
//        workManager.enqueue(request)
//        workID = request.id
//        wifiWorkInfo = workManager.getWorkInfoByIdFlow(request.id)


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
            Log.i("TEST", "refresh: $e")
            e.printStackTrace()
        }
    }
}