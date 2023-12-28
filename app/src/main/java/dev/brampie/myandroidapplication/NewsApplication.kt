package dev.brampie.myandroidapplication

import android.app.Application
import dev.brampie.myandroidapplication.data.AppContainer
import dev.brampie.myandroidapplication.data.DefaultAppContainer

class NewsApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(applicationContext)
    }
}