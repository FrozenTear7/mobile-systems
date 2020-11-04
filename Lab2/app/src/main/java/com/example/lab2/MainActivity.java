package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static String IP = "ip";
    public static String NICK = "nick";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText serverEditText = findViewById(R.id.serverEditText);
        EditText nicknameEditText = findViewById(R.id.nicknameEditText);

        Button startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SimpleChatActivity.class);
            intent.putExtra(IP, serverEditText.getText().toString());
            intent.putExtra(NICK, nicknameEditText.getText().toString());
            startActivity(intent);
        });
    }
}