package dev.brampie.myandroidapplication

import android.app.Application
import dev.brampie.myandroidapplication.data.AppContainer
import dev.brampie.myandroidapplication.data.DefaultAppContainer

/**
 * Custom [Application] class for the Final Space app.
 * Initializes the [AppContainer] for dependency injection and application-wide access.
 */
class FinalSpaceApplication: Application() {
    /**
     * The container that holds application dependencies.
     */
    lateinit var container: AppContainer

    /**
     * Called when the application is first created. Initializes the [AppContainer] with the application context.
     */
    override fun onCreate() {
        super.onCreate()
        // Initialize the AppContainer with the application context
        container = DefaultAppContainer(applicationContext)
    }
}