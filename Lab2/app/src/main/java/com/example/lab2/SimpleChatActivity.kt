package com.example.lab2

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SimpleChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_chat)

        val nicknameChatTextView = findViewById<TextView>(R.id.nicknameChatTextView)
        val chatMessagesListView = findViewById<ListView>(R.id.chatMessagesListView)

        val server = intent.getStringExtra(MainActivity.IP)
        val nick = intent.getStringExtra(MainActivity.NICK)

        nicknameChatTextView.text = nick

        val listItems = ArrayList<String>()
        val adapter: ArrayAdapter<String>
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        chatMessagesListView.adapter = adapter

        val myHandler: Handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                listItems.add(
                    "[" + msg.data.getString("NICK").toString() + "]" +
                            msg.data.getString("MSG")
                )
                adapter.notifyDataSetChanged()
                chatMessagesListView.setSelection(listItems.size - 1)
            }
        }
    }
}