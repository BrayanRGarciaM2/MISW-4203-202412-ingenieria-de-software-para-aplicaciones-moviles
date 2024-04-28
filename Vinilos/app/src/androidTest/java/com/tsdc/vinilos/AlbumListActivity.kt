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

class AlbumListActivity {
    @get:Rule
    val activityRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testScreenAlbumList() {
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
        }

    }

    @Test
    fun testAlbumListTitle() {
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
            activityRule.onNodeWithTag("AlbumListTitle").assertIsDisplayed()
        }

    }

    @Test
    fun testAlbumListItem() {
        // Add a delay to give time for the view to load

        activityRule.onNodeWithText("Visitante").performClick()
        activityRule.onNodeWithText("Albumes").performClick()
        var errorNode = activityRule.onNodeWithText("No se encontraron álbumes para mostrar")
        if(errorNode.isDisplayed()){
            errorNode.assertIsDisplayed()
        }else{
            activityRule.onNodeWithText("Nombre del álbum").assertIsDisplayed()
            Thread.sleep(2000)
            activityRule.onAllNodesWithTag("AlbumListItem").onFirst().assertIsDisplayed()
        }

    }
}