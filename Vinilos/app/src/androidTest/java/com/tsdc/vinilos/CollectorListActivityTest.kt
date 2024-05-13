package com.tsdc.vinilos

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class CollectorListActivityTest {
    @get:Rule
    val activityRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testScreenCollectorList() {
        // Add a delay to give time for the view to load
        activityRule.onNodeWithText("Coleccionista").performClick()
        activityRule.onNodeWithText("Albumes").assertIsNotDisplayed()
        activityRule.onNodeWithText("Digíte su correo").assertIsDisplayed()
        activityRule.onNodeWithText("Digíte su correo").performClick()
        activityRule.onNodeWithText("Digíte su correo").performTextInput("contact@compose.academy")
        activityRule.onNodeWithText("Identificarse").assertIsDisplayed()
        activityRule.onNodeWithText("Identificarse").performClick()
        activityRule.onNodeWithText("Albumes").assertIsDisplayed()
        activityRule.onNodeWithText("Artistas").assertIsDisplayed()
        activityRule.onNodeWithText("Agregar artista").assertIsDisplayed()
        activityRule.onNodeWithText("Crear un álbum").assertIsDisplayed()
        activityRule.onNodeWithText("Volver").assertIsDisplayed()
        activityRule.onNodeWithText("Coleccionistas").assertIsDisplayed()
        activityRule.onNodeWithText("Coleccionistas").performClick()
        val errorNode = activityRule.onNodeWithText("No se encontraron coleccionistas para mostrar")
        if(errorNode.isDisplayed()){
            errorNode.assertIsDisplayed()
        }else{
            activityRule.onNodeWithTag("CollectorItem").assertIsDisplayed()
            Thread.sleep(2000)
        }
    }

    @Test
    fun testScreenCollector() {
        // Add a delay to give time for the view to load
        activityRule.onNodeWithText("Coleccionista").performClick()
        activityRule.onNodeWithText("Albumes").assertIsNotDisplayed()
        activityRule.onNodeWithText("Digíte su correo").assertIsDisplayed()
        activityRule.onNodeWithText("Digíte su correo").performClick()
        activityRule.onNodeWithText("Identificarse").assertIsDisplayed()
        activityRule.onNodeWithText("Identificarse").performClick()
        activityRule.onNodeWithText("Albumes").assertIsDisplayed()
        activityRule.onNodeWithText("Artistas").assertIsDisplayed()
        activityRule.onNodeWithText("Agregar artista").assertIsDisplayed()
        activityRule.onNodeWithText("Crear un álbum").assertIsDisplayed()
        activityRule.onNodeWithText("Volver").assertIsDisplayed()
        activityRule.onNodeWithText("Volver").performClick()

    }

}
