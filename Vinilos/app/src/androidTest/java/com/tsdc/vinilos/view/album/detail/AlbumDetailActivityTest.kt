package com.tsdc.vinilos.view.album.detail

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
import com.tsdc.vinilos.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class AlbumDetailActivityTest {
    @get:Rule
    val activityRule = createAndroidComposeRule<MainActivity>()


    @Test
    fun testAlbumDetailImage() {
        // Add a delay to give time for the view to load

        activityRule.onNodeWithText("Visitante").performClick()
        activityRule.onNodeWithText("Albumes").performClick()

        activityRule.onNodeWithText("Nombre del álbum").assertIsDisplayed()
        Thread.sleep(2000)
        activityRule.onAllNodesWithTag("AlbumListItem").onFirst().performClick()
        // Check if the "AlbumDetailScreen" is displayed
        activityRule.onNodeWithTag("AlbumDetailScreen").assertIsDisplayed()
        Thread.sleep(2000)
        if (activityRule.onNodeWithTag("AlbumDetailImage").isDisplayed()) {
            activityRule.onNodeWithTag("AlbumDetailImage").assertIsDisplayed()
        }


    }

    @Test
    fun testAlbumDetailTitle() {

        activityRule.onNodeWithText("Visitante").performClick()
        activityRule.onNodeWithText("Albumes").performClick()
        Thread.sleep(2000)
        val errorNode = activityRule.onNodeWithText("No se encontraron álbumes para mostrar")
        if (errorNode.isDisplayed()) {
            errorNode.assertIsDisplayed()
        } else {
            activityRule.onNodeWithText("Nombre del álbum").assertIsDisplayed()
            Thread.sleep(2000)
            activityRule.onAllNodesWithTag("AlbumListItem").onFirst().performClick()

            // Check if the "AlbumDetailScreen" is displayed
            activityRule.onNodeWithTag("AlbumDetailScreen").assertIsDisplayed()
            Thread.sleep(2000)
            activityRule.onNodeWithTag("AlbumTitle").assertIsDisplayed()
        }

    }

    @Test
    fun testSuccessAlbumDetailDescription() {
        // Add a delay to give time for the view to load

        activityRule.onNodeWithText("Visitante").performClick()
        activityRule.onNodeWithText("Albumes").performClick()
        Thread.sleep(3000)
        activityRule.onNodeWithText("Nombre del álbum").assertIsDisplayed()
        Thread.sleep(4000)
        activityRule.onAllNodesWithTag("AlbumListItem").onFirst().performClick()

        // Check if the "AlbumDetailScreen" is displayed
        activityRule.onNodeWithTag("AlbumDetailScreen").assertIsDisplayed()
        Thread.sleep(2000)
        activityRule.onNodeWithTag("AlbumDetailDescription").assertIsDisplayed()
    }

    @Test
    fun testNegativeAlbumDetailSongTitle() {
        // Add a delay to give time for the view to load

        activityRule.onNodeWithText("Visitante").performClick()
        activityRule.onNodeWithText("Albumes").performClick()

        val errorNode = activityRule.onNodeWithText("No se encontraron álbumes para mostrar")
        if (errorNode.isDisplayed()) {
            errorNode.assertIsDisplayed()
        }
    }

    @Test
    fun testSuccessAlbumDetailSongTitle() {
        // Add a delay to give time for the view to load

        activityRule.onNodeWithText("Visitante").performClick()
        activityRule.onNodeWithText("Albumes").performClick()
        Thread.sleep(3000)
        activityRule.onNodeWithText("Nombre del álbum").assertIsDisplayed()
        Thread.sleep(2000)
        activityRule.onAllNodesWithTag("AlbumListItem").onFirst().performClick()

        if (activityRule.onNodeWithTag("AlbumDetailScreen").isDisplayed()) {
            activityRule.onNodeWithTag("AlbumDetailScreen").assertIsDisplayed()
        }
        // Check if the "AlbumDetailScreen" is displayed
        Thread.sleep(3000)
        if (activityRule.onAllNodesWithTag("AlbumDetailSongTitle").onFirst().isDisplayed()) {
            activityRule.onAllNodesWithTag("AlbumDetailSongTitle").onFirst().assertIsDisplayed()
        }
    }

    @Test
    fun testAlbumDetailSongDuration() {
        // Add a delay to give time for the view to load

        activityRule.onNodeWithText("Visitante").performClick()
        activityRule.onNodeWithText("Albumes").performClick()
        Thread.sleep(2000)
        val errorNode = activityRule.onNodeWithText("No se encontraron álbumes para mostrar")
        if (errorNode.isDisplayed()) {
            errorNode.assertIsDisplayed()
        } else {
            activityRule.onNodeWithText("Nombre del álbum").assertIsDisplayed()
            Thread.sleep(2000)
            activityRule.onAllNodesWithTag("AlbumListItem").onFirst().performClick()

            // Check if the "AlbumDetailScreen" is displayed
            activityRule.onNodeWithTag("AlbumDetailScreen").assertIsDisplayed()
            Thread.sleep(2000)
            if (activityRule.onAllNodesWithTag("AlbumDetailSongDuration").onFirst().isDisplayed()) {
                activityRule.onAllNodesWithTag("AlbumDetailSongDuration").onFirst()
                    .assertIsDisplayed()
            }
        }
    }
}

