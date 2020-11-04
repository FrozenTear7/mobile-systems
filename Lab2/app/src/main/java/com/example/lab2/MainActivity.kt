package com.example.lab2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    companion object {
        val IP = "ip"
        val NICK = "nick"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serverEditText = findViewById<EditText>(R.id.serverEditText)
        val nicknameEditText = findViewById<EditText>(R.id.nicknameEditText)

        val startButton = findViewById<Button>(R.id.startButton)

        startButton.setOnClickListener {
            val server = serverEditText.text.toString()
            val nickname = nicknameEditText.text.toString()

            if (server != "" && nickname != "") {
                val intent = Intent(this@MainActivity, SimpleChatActivity::class.java)
                intent.putExtra(IP, server)
                intent.putExtra(NICK, nickname)
                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    "Please provide correct server and nickname",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}