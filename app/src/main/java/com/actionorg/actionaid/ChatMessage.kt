package com.actionorg.actionaid

data class ChatMessage(
    val text: String,
    val isUser: Boolean
    , var isExpanded: Boolean = false// true if user prompt, false if AI response
)
