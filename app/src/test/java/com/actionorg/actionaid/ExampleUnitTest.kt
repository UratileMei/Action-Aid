package com.actionorg.actionaid

import android.view.View
import com.google.ai.client.generativeai.GenerativeModel
import org.junit.Test

import org.junit.Assert.*
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun testAddMessage() {
        // Arrange
        val activity = AIActivity()
        val message = "Test Message"
        val isUser = true

        // Act
        activity.addMessage(message, isUser)

        // Assert
        assertEquals(1, activity.chatMessages.size)
        assertEquals("Test Message", activity.chatMessages[0].text)
        assertEquals(true, activity.chatMessages[0].isUser)
    }
    @Test
    fun testEmptyPromptNotSent() {
        // Arrange
        val activity = AIActivity()
        activity.promptEditText.setText("")

        // Act
        activity.sendButton.performClick()

        // Assert
        assertEquals(0, activity.chatMessages.size) // No messages should be added
    }
    @Test
    suspend fun testAPIOverloadHandling() {
        // Arrange
        val mockModel = mock(GenerativeModel::class.java)
        `when`(mockModel.generateContent(anyString())).thenThrow(RuntimeException("AI overloaded"))
        val activity = AIActivity()

        // Act
        activity.geminiAIAPI(View(activity))

        // Assert
        val lastMessage = activity.chatMessages.last()
        assertEquals("AI is currently overloaded. Please try again later.", lastMessage.text)
        assertEquals(false, lastMessage.isUser)
    }


}