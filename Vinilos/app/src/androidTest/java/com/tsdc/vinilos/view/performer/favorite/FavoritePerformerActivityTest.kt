package com.tsdc.vinilos.view.performer.favorite

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.tsdc.vinilos.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class FavoritePerformerActivityTest {
    @get:Rule
    val activityRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testArtistsAreDisplayed() {
        initSteps()
        Thread.sleep(1000)
        activityRule.onNodeWithText("Agregar artista").assertIsDisplayed()
        activityRule.onNodeWithText("Agregar artista").performClick()
        Thread.sleep(3000)
        activityRule.onAllNodesWithTag("FavoritePerformerListItem").onFirst().assertIsDisplayed()
        activityRule.onAllNodesWithTag("CheckboxFavoritePerformerItem").onFirst()
            .assertIsDisplayed()
        activityRule.onAllNodesWithTag("FavoritePerformerItemName").onFirst().assertIsDisplayed()
    }

    @Test
    fun testSelectAFavoriteArtist() {
        initSteps()
        Thread.sleep(1000)
        activityRule.onNodeWithText("Agregar artista").assertIsDisplayed()
        activityRule.onNodeWithText("Agregar artista").performClick()
        Thread.sleep(3000)
        activityRule.onAllNodesWithTag("CheckboxFavoritePerformerItem").onFirst().performClick()
        activityRule.onNodeWithText("Asociar artista").performClick()
        Thread.sleep(100)
        activityRule.onNodeWithText("Success!").assertIsDisplayed()
    }

    private fun initSteps() {
        activityRule.onNodeWithText("Coleccionista").performClick()
        activityRule.onNodeWithText("Albumes").assertIsNotDisplayed()
        activityRule.onNodeWithText("Digite su correo").assertIsDisplayed()
        activityRule.onNodeWithText("Digite su correo").performClick()
        activityRule.onNodeWithText("Digite su correo").performTextInput("manollo@caracol.com.co")
        activityRule.onNodeWithText("Identificarse").assertIsDisplayed()
        activityRule.onNodeWithText("Identificarse").performClick()
    }
}
