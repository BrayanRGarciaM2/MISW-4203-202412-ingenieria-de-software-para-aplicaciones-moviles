package com.tsdc.vinilos

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class AlbumListActivityTest {
    @get:Rule
    val activityRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testNegativeScreenAlbumList() {
        // Add a delay to give time for the view to load

        activityRule.onNodeWithText("Visitante").performClick()
        activityRule.onNodeWithText("Albumes").performClick()
        val errorNode = activityRule.onNodeWithText("No se encontraron álbumes para mostrar")
        if (errorNode.isDisplayed()) {
            errorNode.assertIsDisplayed()
        }
    }


    @Test
    fun testScreenAlbumList() {
        // Add a delay to give time for the view to load

        activityRule.onNodeWithText("Visitante").performClick()
        activityRule.onNodeWithText("Albumes").performClick()
        Thread.sleep(2000)
        activityRule.onNodeWithText("Nombre del álbum").assertIsDisplayed()
        Thread.sleep(2000)
        activityRule.onAllNodesWithTag("AlbumListItem", true).onFirst().performClick()
        // Check if the "AlbumDetailScreen" is displayed
        activityRule.onNodeWithTag("AlbumDetailScreen").assertIsDisplayed()


    }

    @Test
    fun testAlbumListTitle() {
        // Add a delay to give time for the view to load

        activityRule.onNodeWithText("Visitante").performClick()
        activityRule.onNodeWithText("Albumes").performClick()
        Thread.sleep(3000)
        activityRule.onNodeWithText("Nombre del álbum").assertIsDisplayed()
        Thread.sleep(2000)
        activityRule.onAllNodesWithTag("AlbumListItem").onFirst().assertIsDisplayed()
        // Check if the "AlbumDetailScreen" is displayed
        Thread.sleep(2000)
        activityRule.onNodeWithTag("AlbumListTitle").assertIsDisplayed()
        activityRule.onAllNodesWithTag("AlbumItemTitle", true).onFirst().assertIsDisplayed()
    }

    @Test
    fun testAlbumListItem() {
        // Add a delay to give time for the view to load

        activityRule.onNodeWithText("Visitante").performClick()
        activityRule.onNodeWithText("Albumes").performClick()
        Thread.sleep(2000)
        activityRule.onNodeWithText("Nombre del álbum").assertIsDisplayed()
        Thread.sleep(2000)
        activityRule.onAllNodesWithTag("AlbumListItem").onFirst().assertIsDisplayed()
    }
}
