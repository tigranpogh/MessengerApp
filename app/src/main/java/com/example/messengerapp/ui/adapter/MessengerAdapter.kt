package com.example.messengerapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.messengerapp.databinding.MessagesItemBinding

class MessengerAdapter : RecyclerView.Adapter<MessengerAdapter.MessagesHolder>() {

    private var messagesList = emptyList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesHolder {
        val binding =
            MessagesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessagesHolder(binding)
    }

    override fun getItemCount(): Int {
        return messagesList.size
    }

    override fun onBindViewHolder(holder: MessagesHolder, position: Int) {
        holder.bind(messagesList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(messagesList: List<String>) {
        this.messagesList = messagesList
        notifyDataSetChanged()
    }

    inner class MessagesHolder(private val binding: MessagesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: String) {
            binding.txtMessage.text = message
        }
    }
}