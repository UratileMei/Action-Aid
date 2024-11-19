package com.actionorg.actionaid

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.ai.client.generativeai.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class AIActivity : AppCompatActivity() {
    lateinit var account_img : ImageView
    lateinit var donate_img : ImageView
    lateinit var home_img : ImageView
    lateinit var gallery_img : ImageView
    lateinit var news_img : ImageView
    lateinit var contact_img : ImageView
    lateinit var about_img : ImageView
    //
    private lateinit var chatAdapter: ChatAdapter
    private val chatMessages = mutableListOf<ChatMessage>()
    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var sendButton: Button
    private lateinit var promptEditText: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai)


        chatRecyclerView = findViewById(R.id.chatRecyclerView)
        sendButton = findViewById(R.id.sendButton)
        promptEditText  = findViewById(R.id.promptEditText)

        // Set up RecyclerView and Adapter
        chatAdapter = ChatAdapter(chatMessages)
        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter = chatAdapter

        // Button to send prompt
        sendButton.setOnClickListener {
            val prompt = promptEditText.text.toString()
            if (prompt.isNotBlank()) {
                addMessage(prompt, isUser = true)
                geminiAIAPI(it)
                promptEditText.text.clear()
            }

        }
    }
    private fun addMessage(text: String, isUser: Boolean) {
        val chatMessage = ChatMessage(text, isUser)
        chatAdapter.addMessage(chatMessage)
        chatRecyclerView.scrollToPosition(chatMessages.size - 1) // Auto-scroll to the latest message
    }

    private fun geminiAIAPI(view: View) {
        val promptTextView = findViewById<EditText>(R.id.promptEditText)
        val generativeModel = GenerativeModel(
            modelName = "gemini-1.5-flash",
            apiKey = "API_Key",

            )

        val prompt = promptTextView.text.toString()
        MainScope().launch {
            try {
                val response = generativeModel.generateContent(prompt)
                addMessage(response.text.toString(), isUser = false)
            } catch (e: Exception) {
                addMessage("AI is currently overloaded. Please try again later.", isUser = false)
                e.printStackTrace() // Log the error for debugging
            }
        }
    }

}