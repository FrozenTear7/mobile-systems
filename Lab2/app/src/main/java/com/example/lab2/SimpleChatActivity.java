package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class SimpleChatActivity extends AppCompatActivity {
    private String myTopic = "AGH/FrozenTear7";

    private ListView chatMessagesListView;

    private EditText messageEditText;

    String server;
    String nick;

    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;

    Handler myHandler = new MyHandler(this);

    MqttClient sampleClient = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_chat);

        TextView nicknameChatTextView = findViewById(R.id.nicknameChatTextView);
        chatMessagesListView = findViewById(R.id.chatMessagesListView);
        messageEditText = findViewById(R.id.messageEditText);
        Button sendButton = findViewById(R.id.sendButton);

        server = getIntent().getStringExtra(MainActivity.IP);
        nick = getIntent().getStringExtra(MainActivity.NICK);

        listItems = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);

        nicknameChatTextView.setText(getIntent().getStringExtra(MainActivity.NICK));

        chatMessagesListView.setAdapter(adapter);

        sendButton.setOnClickListener(this::postOnClick);

        new Thread(this::startMQTT).start();
    }

    public void postOnClick(View view) {
        Message msg = myHandler.obtainMessage();
        Bundle b = new Bundle();
        b.putString("NICK", nick);
        b.putString("MSG", messageEditText.getText().toString());
        msg.setData(b);

        try {
            sampleClient.publish(myTopic, new MqttMessage(messageEditText.getText().toString().getBytes()));
        } catch (MqttException e) {
            Toast.makeText(getApplicationContext(), "Could not send message", Toast.LENGTH_SHORT).show();
        }
    }

    private static class MyHandler extends Handler {
        private final WeakReference<SimpleChatActivity> sActivity;

        MyHandler(SimpleChatActivity activity) {
            sActivity = new WeakReference<>(activity);
        }

        public void handleMessage(Message msg) {
            SimpleChatActivity activity = sActivity.get();
            activity.listItems.add(msg.getData().getString("MSG"));
            activity.adapter.notifyDataSetChanged();
            activity.chatMessagesListView.setSelection(activity.listItems.size() - 1);
        }
    }

    private void startMQTT() {
        String clientId;
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            String broker = "tcp://" + server + ":1883";
            clientId = nick;
            sampleClient = new MqttClient(broker, clientId, persistence);
            sampleClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable throwable) {
                    System.out.println("Connection lost");
                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) {
                    System.out.println(s);
                    System.out.println(mqttMessage);
                    Message msg = myHandler.obtainMessage();
                    Bundle b = new Bundle();
                    b.putString("MSG", mqttMessage.toString());
                    msg.setData(b);
                    myHandler.sendMessage(msg);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                    System.out.println("Delivery complete");
                }
            });
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: " + broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            sampleClient.subscribe(myTopic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (sampleClient != null) {
            try {
                sampleClient.disconnect();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }
}