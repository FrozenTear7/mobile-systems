package com.example.lab2

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.lang.ref.WeakReference


class SimpleChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_chat)

        val nicknameChatTextView = findViewById<TextView>(R.id.nicknameChatTextView)

        val chatMessagesListView = findViewById<ListView>(R.id.chatMessagesListView)

        val messageEditText = findViewById<EditText>(R.id.messageEditText)

        val sendButton = findViewById<Button>(R.id.sendButton)

        val server = intent.getStringExtra(MainActivity.IP)
        val nick = intent.getStringExtra(MainActivity.NICK)

        nicknameChatTextView.text = nick

        val listItems = ArrayList<String>()
        val adapter: ArrayAdapter<String>
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        chatMessagesListView.adapter = adapter

        val myHandler: Handler = object : Handler() {
            @SuppressLint("HandlerLeak")
            override fun handleMessage(msg: Message) {
                listItems.add(
                    "[" + msg.data.getString("NICK").toString() + "]" +
                            msg.data.getString("MSG")
                )
                adapter.notifyDataSetChanged()
                chatMessagesListView.setSelection(listItems.size - 1)
            }
        }

//        class MyHandler(activity: SimpleChatActivity?) : Handler() {
//            private val sActivity: WeakReference<SimpleChatActivity> =
//                WeakReference<SimpleChatActivity>(activity)
//
//            override fun handleMessage(msg: Message) {
//                val activity: SimpleChatActivity? = sActivity.get()
//                activity!!.listItems.add(
//                    "[" + msg.data.getString("NICK") + "]" +
//                            msg.data.getString("MSG")
//                )
//                activity.adapter.notifyDataSetChanged()
//                activity.chatMessagesListView.setSelection(activity.listItems.size() - 1)
//            }
//        }

//        val myHandler: Handler = MyHandler(this)

        fun postOnClick() {
            val msg: Message = myHandler.obtainMessage()
            val b = Bundle()
            b.putString("NICK", nick)
            b.putString("MSG", messageEditText.text.toString())
            msg.data = b
            myHandler.sendMessage(msg)
        }

        sendButton.setOnClickListener {
            postOnClick()
        }

        MqttClient sampleClient=null;
        private void startMQTT(){
            String clientId;
            MemoryPersistence persistence = new MemoryPersistence();
            try {
                String broker = "tcp://"+ip+":1883";
                clientId = nick;
                sampleClient = new MqttClient(broker, clientId, persistence);
                sampleClient.setCallback(new MqttCallback() {
                    //TODO
                });
                MqttConnectOptions connOpts = new MqttConnectOptions();
                connOpts.setCleanSession(true);
                System.out.println("Connecting to broker: "+broker);
                sampleClient.connect(connOpts);
                System.out.println("Connected");
                sampleClient.subscribe("#");
            } catch (MqttException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}