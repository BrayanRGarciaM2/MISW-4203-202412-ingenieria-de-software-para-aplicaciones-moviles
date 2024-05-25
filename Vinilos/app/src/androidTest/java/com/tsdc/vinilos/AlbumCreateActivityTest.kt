package com.tsdc.vinilos

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class AlbumCreateActivityTest {

    @get:Rule
    val activityRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testCreateWithErrorTwoFieldsRequiresAlbum() {
        activityRule.onNodeWithText("Coleccionista").performClick()
        activityRule.onNodeWithText("Identificarse").performClick()
        activityRule.onNodeWithText("Crear un álbum").performClick()
        activityRule.onNodeWithText("Nombre").performTextInput("Margarita")
        activityRule.onNodeWithText("Descripción").performTextInput("Forero")
        activityRule.onNodeWithText("Imagen").performTextInput("https://cdn.pixabay.com/photo/2024/02/26/19/39/monochrome-image-8598798_640.jpg")
        activityRule.onNodeWithText("Registrar álbum").performClick()
        activityRule.onNodeWithText("Campo compañia es obligatorio").assertIsDisplayed()
        activityRule.onNodeWithText("Campo género es obligatorio").assertIsDisplayed()
    }

    @Test
    fun testCreateWithErrorInImageAlbum() {
        activityRule.onNodeWithText("Coleccionista").performClick()
        activityRule.onNodeWithText("Identificarse").performClick()
        activityRule.onNodeWithText("Crear un álbum").performClick()
        activityRule.onNodeWithText("Nombre").performTextInput("Margarita")
        activityRule.onNodeWithText("Descripción").performTextInput("Forero")
        activityRule.onNodeWithText("Imagen").performTextInput("paola")
        activityRule.onNodeWithText("Registrar álbum").performClick()
        activityRule.onNodeWithText("La URL debe empezar con https:// una imagen válida (extensiones permitidas: jpg, jpeg, png, gif)").assertIsDisplayed()
        activityRule.onNodeWithText("Campo compañia es obligatorio").assertIsDisplayed()
        activityRule.onNodeWithText("Campo género es obligatorio").assertIsDisplayed()
    }

    @Test
    fun testCreateWithErrorInDescriptionError() {
        activityRule.onNodeWithText("Coleccionista").performClick()
        activityRule.onNodeWithText("Identificarse").performClick()
        activityRule.onNodeWithText("Crear un álbum").performClick()
        activityRule.onNodeWithText("Nombre").performTextInput("Margarita")
        activityRule.onNodeWithText("Imagen").performTextInput("paola")
        activityRule.onNodeWithText("Registrar álbum").performClick()
        activityRule.onNodeWithText("La URL debe empezar con https:// una imagen válida (extensiones permitidas: jpg, jpeg, png, gif)").assertIsDisplayed()
        activityRule.onNodeWithText("Campo compañia es obligatorio").assertIsDisplayed()
        activityRule.onNodeWithText("Campo género es obligatorio").assertIsDisplayed()
        activityRule.onNodeWithText("Campo descripción es obligatorio").assertIsDisplayed()
    }

    @Test
    fun testCreateWithErrorInNameError() {
        activityRule.onNodeWithText("Coleccionista").performClick()
        activityRule.onNodeWithText("Identificarse").performClick()
        activityRule.onNodeWithText("Crear un álbum").performClick()
        activityRule.onNodeWithText("Imagen").performTextInput("paola")
        activityRule.onNodeWithText("Registrar álbum").performClick()
        activityRule.onNodeWithText("La URL debe empezar con https:// una imagen válida (extensiones permitidas: jpg, jpeg, png, gif)").assertIsDisplayed()
        activityRule.onNodeWithText("Campo compañia es obligatorio").assertIsDisplayed()
        activityRule.onNodeWithText("Campo género es obligatorio").assertIsDisplayed()
        activityRule.onNodeWithText("Campo descripción es obligatorio").assertIsDisplayed()
        activityRule.onNodeWithText("Campo nombre es obligatorio").assertIsDisplayed()

    }

    @Test
    fun testCreateWithErrorImage() {
        activityRule.onNodeWithText("Coleccionista").performClick()
        activityRule.onNodeWithText("Identificarse").performClick()
        activityRule.onNodeWithText("Crear un álbum").performClick()
        activityRule.onNodeWithText("Registrar álbum").performClick()
        activityRule.onNodeWithText("Campo género es obligatorio").assertIsDisplayed()
        activityRule.onNodeWithText("Campo descripción es obligatorio").assertIsDisplayed()
        activityRule.onNodeWithText("Campo nombre es obligatorio").assertIsDisplayed()
        activityRule.onNodeWithText("Campo imagen es obligatorio").assertIsDisplayed()
    }

    @Test
    fun testCreateAlbum() {

        activityRule.onNodeWithText("Coleccionista").performClick()
        activityRule.onNodeWithText("Identificarse").performClick()
        activityRule.onNodeWithText("Crear un álbum").performClick()
        activityRule.onNodeWithText("Nombre").performTextInput("Margarita")
        activityRule.onNodeWithText("Descripción").performTextInput("Forero")
        activityRule.onNodeWithText("Imagen").performTextInput("https://cdn.pixabay.com/photo/2024/02/26/19/39/monochrome-image-8598798_640.jpg")
        activityRule.onNodeWithTag("recordLabelDropdownMenu").performClick()
        activityRule.onAllNodesWithTag("recordLabelDropdownItem").onFirst().performClick()
        activityRule.onAllNodesWithTag("recurrenceDropdownMenu").onFirst().performClick()
        activityRule.onAllNodesWithTag("recurrenceDropdownItem").onFirst().performClick()
        activityRule.onNodeWithText("Registrar álbum").performClick()
        activityRule.onNodeWithText("Crear un álbum").isDisplayed()
    }
}