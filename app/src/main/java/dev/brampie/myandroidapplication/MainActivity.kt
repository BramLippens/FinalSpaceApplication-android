package dev.brampie.myandroidapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import dev.brampie.myandroidapplication.ui.FinalSpaceApp
import dev.brampie.myandroidapplication.ui.components.NavigationType
import dev.brampie.myandroidapplication.ui.theme.MyAndroidApplicationTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAndroidApplicationTheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val windowSize = calculateWindowSizeClass(this)

                    when(windowSize.widthSizeClass){
                        WindowWidthSizeClass.Compact -> {
                            FinalSpaceApp(navigationType = NavigationType.BOTTOM_NAVIGATION)
                        }
                        WindowWidthSizeClass.Medium -> {
                            FinalSpaceApp(navigationType = NavigationType.NAVIGATION_RAIL)
                        }
                        WindowWidthSizeClass.Expanded -> {
                            FinalSpaceApp(navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER)
                        }
                    }
                }
            }
        }
    }
}