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
import com.tsdc.vinilos.view.album.detail.AlbumDetailActivity
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class AlbumDetailActivity {
    @get:Rule
    val activityRule = createAndroidComposeRule<MainActivity>()
    @Test
    fun testAlbumDetailImage() {
        // Add a delay to give time for the view to load

        activityRule.onNodeWithText("Visitante").performClick()
        activityRule.onNodeWithText("Albumes").performClick()
        var errorNode = activityRule.onNodeWithText("No se encontraron álbumes para mostrar")
        if(errorNode.isDisplayed()){
            errorNode.assertIsDisplayed()
        }else{
            activityRule.onNodeWithText("Nombre del álbum").assertIsDisplayed()
            Thread.sleep(2000)
            activityRule.onAllNodesWithTag("AlbumListItem").onFirst().performClick()
            // Check if the "AlbumDetailScreen" is displayed
            activityRule.onNodeWithTag("AlbumDetailScreen").assertIsDisplayed()
            Thread.sleep(2000)
            activityRule.onNodeWithTag("AlbumDetailImage").assertIsDisplayed()
        }


    }
    @Test
    fun testAlbumDetailTitle() {
        // Add a delay to give time for the view to load

        activityRule.onNodeWithText("Visitante").performClick()
        activityRule.onNodeWithText("Albumes").performClick()
        var errorNode = activityRule.onNodeWithText("No se encontraron álbumes para mostrar")
        if(errorNode.isDisplayed()){
            errorNode.assertIsDisplayed()
        }else {
            activityRule.onNodeWithText("Nombre del álbum").assertIsDisplayed()
            Thread.sleep(2000)
            activityRule.onAllNodesWithTag("AlbumListItem").onFirst().performClick()

            // Check if the "AlbumDetailScreen" is displayed
            activityRule.onNodeWithTag("AlbumDetailScreen").assertIsDisplayed()
            Thread.sleep(2000)
            activityRule.onNodeWithTag("AlbumDetailTitle").assertIsDisplayed()
        }

    }
    @Test
    fun testAlbumDetailDescription() {
        // Add a delay to give time for the view to load

        activityRule.onNodeWithText("Visitante").performClick()
        activityRule.onNodeWithText("Albumes").performClick()
        var errorNode = activityRule.onNodeWithText("No se encontraron álbumes para mostrar")
        if(errorNode.isDisplayed()){
            errorNode.assertIsDisplayed()
        }else {
            activityRule.onNodeWithText("Nombre del álbum").assertIsDisplayed()
            Thread.sleep(2000)
            activityRule.onAllNodesWithTag("AlbumListItem").onFirst().performClick()

            // Check if the "AlbumDetailScreen" is displayed
            activityRule.onNodeWithTag("AlbumDetailScreen").assertIsDisplayed()
            Thread.sleep(2000)
            activityRule.onNodeWithTag("AlbumDetailDescription").assertIsDisplayed()
        }
    }

    @Test
    fun testAlbumDetailSongTitle() {
        // Add a delay to give time for the view to load

        activityRule.onNodeWithText("Visitante").performClick()
        activityRule.onNodeWithText("Albumes").performClick()
        var errorNode = activityRule.onNodeWithText("No se encontraron álbumes para mostrar")
        if(errorNode.isDisplayed()){
            errorNode.assertIsDisplayed()
        }else {
            activityRule.onNodeWithText("Nombre del álbum").assertIsDisplayed()
            Thread.sleep(2000)
            activityRule.onAllNodesWithTag("AlbumListItem").onFirst().performClick()

            // Check if the "AlbumDetailScreen" is displayed
            activityRule.onNodeWithTag("AlbumDetailScreen").assertIsDisplayed()
            Thread.sleep(3000)
            activityRule.onAllNodesWithTag("AlbumDetailSongTitle").onFirst().assertIsDisplayed()
        }
    }

    @Test
    fun testAlbumDetailSongDuration() {
        // Add a delay to give time for the view to load

        activityRule.onNodeWithText("Visitante").performClick()
        activityRule.onNodeWithText("Albumes").performClick()
        var errorNode = activityRule.onNodeWithText("No se encontraron álbumes para mostrar")
        if(errorNode.isDisplayed()){
            errorNode.assertIsDisplayed()
        }else {
            activityRule.onNodeWithText("Nombre del álbum").assertIsDisplayed()
            Thread.sleep(2000)
            activityRule.onAllNodesWithTag("AlbumListItem").onFirst().performClick()

            // Check if the "AlbumDetailScreen" is displayed
            activityRule.onNodeWithTag("AlbumDetailScreen").assertIsDisplayed()
            Thread.sleep(2000)
            activityRule.onAllNodesWithTag("AlbumDetailSongDuration").onFirst().assertIsDisplayed()
        }
    }
}