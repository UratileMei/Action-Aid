package com.actionorg.actionaid
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(private val chatMessages: MutableList<ChatMessage>) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val messageTextView: TextView = view.findViewById(R.id.messageTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = if (viewType == 1) {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user_message, parent, false)
        } else {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_ai_message, parent, false)
        }
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chatMessage = chatMessages[position]

        // Show truncated text if not expanded, otherwise show the full text
        holder.messageTextView.text = if (chatMessage.isExpanded) {
            chatMessage.text
        } else {
            chatMessage.text.take(100) + if (chatMessage.text.length > 100) "… Read More" else ""
        }

        // Toggle expanded state on click
        holder.messageTextView.setOnClickListener {
            chatMessage.isExpanded = !chatMessage.isExpanded
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = chatMessages.size

    override fun getItemViewType(position: Int): Int {
        return if (chatMessages[position].isUser) 1 else 0
    }

    fun addMessage(chatMessage: ChatMessage) {
        chatMessages.add(chatMessage)
        notifyItemInserted(chatMessages.size - 1)
    }
}