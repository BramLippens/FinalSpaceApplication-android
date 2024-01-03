package dev.brampie.myandroidapplication

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.navigation.testing.TestNavHostController
import dev.brampie.myandroidapplication.ui.FinalSpaceApp
import dev.brampie.myandroidapplication.ui.components.NavigationType
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterPageNavigationTest {
    private lateinit var navController: TestNavHostController

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setupNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            FinalSpaceApp(navController = navController, navigationType = NavigationType.BOTTOM_NAVIGATION)
        }
    }

    @Test
    fun NavHost_ClickOnCharacterItem_navigatesToDetailCharacterPage(){
        Thread.sleep(2000)
        composeTestRule.onNodeWithContentDescriptionId(R.string.go_to_characters)
            .performClick()
        composeTestRule.onNodeWithTag("CharacterCard_Gary Goodspeed").assertIsDisplayed()
        composeTestRule.onNodeWithTag("CharacterCard_Gary Goodspeed").performClick()
        Thread.sleep(1000)
        composeTestRule.onNodeWithContentDescription("Image for Gary Goodspeed")
            .assertExists()
    }
}