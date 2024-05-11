package com.tsdc.vinilos.view.artist.detail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.tsdc.vinilos.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ArtistDetailActivityTest {

    @get:Rule
    val activityRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testArtistDetailImage() {
        initSteps()
        Thread.sleep(3000)
        activityRule.onNodeWithText("Artistas").assertIsDisplayed()
        Thread.sleep(3000)
        val artist = activityRule.onAllNodesWithTag("ArtistaListItem").onFirst()
        artist.assertIsDisplayed()
        val name = activityRule.onAllNodesWithContentDescription("Image").onFirst()
        name.performClick()
        Thread.sleep(3000)
        activityRule.onNodeWithTag("ArtistDetailScreen").assertIsDisplayed()
        activityRule.onNodeWithTag("ArtistDetailTitle").assertIsDisplayed()
        activityRule.onNodeWithTag("ArtistDetailImage").assertIsDisplayed()
        activityRule.onNodeWithTag("ArtistTitle").assertIsDisplayed()
        activityRule.onNodeWithTag("ArtistDetailDescription").assertIsDisplayed()
        activityRule.onNodeWithTag("BackButton").assertIsDisplayed()
        activityRule.onNodeWithTag("BackButton").performClick()

    }

    fun initSteps() {
        activityRule.onNodeWithText("Visitante").performClick()
        activityRule.onNodeWithText("Artistas").performClick()
    }
}
