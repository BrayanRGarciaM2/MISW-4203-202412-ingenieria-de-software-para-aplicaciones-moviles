package com.tsdc.vinilos.collector.detail

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
class CollectorDetailTest {

    @get:Rule
    val activityRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testCollectorDetailName() {
        activityRule.onNodeWithText("Visitante").performClick()
        activityRule.onNodeWithText("Coleccionistas").performClick()
        Thread.sleep(4000)
        activityRule.onAllNodesWithTag("CollectorListItem").onFirst().performClick()
        Thread.sleep(4000)
        activityRule.onNodeWithTag("CollectorDetailToolBarName").assertIsDisplayed()
        Thread.sleep(4000)
    }
    @Test
    fun testCollectorDetailEmailAndTelephone() {
        activityRule.onNodeWithText("Visitante").performClick()
        activityRule.onNodeWithText("Coleccionistas").performClick()
        Thread.sleep(4000)
        activityRule.onAllNodesWithTag("CollectorListItem").onFirst().performClick()
        Thread.sleep(4000)
        activityRule.onNodeWithTag("CollectorDetailEmail").assertIsDisplayed()
        Thread.sleep(4000)
        activityRule.onNodeWithTag("CollectorDetailTelephone").assertIsDisplayed()
        Thread.sleep(4000)
    }

    @Test
    fun testSuccessCollectorDetailAlbumsAndPerformers() {
        activityRule.onNodeWithText("Visitante").performClick()
        activityRule.onNodeWithText("Coleccionistas").performClick()
        Thread.sleep(4000)
        activityRule.onAllNodesWithTag("CollectorListItem").onFirst().performClick()
        Thread.sleep(4000)
        activityRule.onNodeWithTag("CollectorDetailAlbumName").assertIsDisplayed()
        Thread.sleep(4000)
        activityRule.onNodeWithTag("CollectorDetailFavoritePerformersName").assertIsDisplayed()
        Thread.sleep(4000)
    }

    @Test
    fun testNegativeCollectorDetailNames() {
        activityRule.onNodeWithText("Visitante").performClick()
        activityRule.onNodeWithText("Coleccionistas").performClick()
        val errorNode = activityRule.onNodeWithText("No se encontrarón colecciones para mostrar")
        if (errorNode.isDisplayed()) {
            errorNode.assertIsDisplayed()
        }
    }

    @Test
    fun testCollectorDetailAlbumImage() {
    activityRule.onNodeWithText("Visitante").performClick()
    activityRule.onNodeWithText("Coleccionistas").performClick()
    Thread.sleep(4000)
    activityRule.onAllNodesWithTag("CollectorListItem").onFirst().performClick()
    Thread.sleep(4000)
    activityRule.onNodeWithTag("CollectorDetailAlbumImage").assertIsDisplayed()
    Thread.sleep(4000)
    }

    @Test
    fun testCollectorDetailFavoritePerformersImage() {
    activityRule.onNodeWithText("Visitante").performClick()
    activityRule.onNodeWithText("Coleccionistas").performClick()
    Thread.sleep(4000)
    activityRule.onAllNodesWithTag("CollectorListItem").onFirst().performClick()
    Thread.sleep(4000)
    activityRule.onNodeWithTag("CollectorFavoritePerformersImage").assertIsDisplayed()
    Thread.sleep(4000)
    }
}