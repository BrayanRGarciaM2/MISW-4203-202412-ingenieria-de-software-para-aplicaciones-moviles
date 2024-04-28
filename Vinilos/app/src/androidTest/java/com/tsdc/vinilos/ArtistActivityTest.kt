package com.tsdc.vinilos

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class ArtistActivityTest {

    @get:Rule
    val activityRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testClickVisitantButtonAndArtistButton() {

        activityRule.onNodeWithText("Visitante").performClick()
        activityRule.onNodeWithText("Artistas").performClick()
        Thread.sleep(2000)
        val artist =  activityRule.onAllNodesWithTag("ArtistaListItem").onFirst()
        artist.assertIsDisplayed()
        val image =  activityRule.onAllNodesWithTag("imagen").onFirst()
        image.assertExists()
    }

    @Test
    fun testClickSecondButton() {
        activityRule.onNodeWithText("Visitante").performClick()
        activityRule.onNodeWithText("Artistas").performClick()
        val name = activityRule.onAllNodesWithTag("name").onFirst()
        name.assertExists()
    }
}