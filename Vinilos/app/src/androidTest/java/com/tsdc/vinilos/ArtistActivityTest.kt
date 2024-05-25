package com.tsdc.vinilos

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
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
    fun testArtistsListValidateImages() {
        Thread.sleep(1000)
        activityRule.onNodeWithText("Visitante").performClick()
        activityRule.onNodeWithText("Artistas").performClick()
        val errorNode = activityRule.onNodeWithTag("AlbumListError")
        if (errorNode.isDisplayed()) {
            errorNode.assertIsDisplayed()
        } else {
            Thread.sleep(2000)
            val artist = activityRule.onAllNodesWithTag("ArtistaListItem").onFirst()
            artist.assertIsDisplayed()
            Thread.sleep(2000)
            activityRule.onAllNodesWithTag("imagen", true).onFirst().assertIsDisplayed()
        }
    }

    @Test
    fun testArtistListValidateNames() {
        Thread.sleep(1000)
        activityRule.onNodeWithText("Visitante").performClick()
        activityRule.onNodeWithText("Artistas").performClick()
        val errorNode = activityRule.onNodeWithText("No se encontraron artistas para mostrar")
        if (errorNode.isDisplayed()) {
            errorNode.assertIsDisplayed()
        } else {
            Thread.sleep(5000)
            val artist = activityRule.onAllNodesWithTag("ArtistaListItem", true).onFirst()
            artist.assertIsDisplayed()
            Thread.sleep(2000)
            activityRule.onAllNodesWithTag("artistName", true).onFirst().assertIsDisplayed()
        }
    }

    @Test
    fun testClickArtistBottom() {
        activityRule.onNodeWithText("Visitante").performClick()
        activityRule.onNodeWithText("Artistas").performClick()
        val errorNode = activityRule.onNodeWithText("No se encontraron artistas para mostrar")
        if(errorNode.isDisplayed()){
            errorNode.assertIsDisplayed()
        }else{
            activityRule.onNodeWithText("Artistas").assertExists()
        }
    }
}
