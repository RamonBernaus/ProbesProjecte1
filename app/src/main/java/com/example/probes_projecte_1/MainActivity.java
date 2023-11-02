package com.example.probes_projecte_1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.probes_projecte_1.Recycler.CreateRecyclerView;
import com.example.probes_projecte_1.Sockets.SocketsConnexion;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SocketManager socketManager;
    private TextView chatTextView;
    private EditText messageEditText, Username, UserPassword;
    private Button sendButton, LoginButton, RecyclerButton, SocketButton;
    private final Context context = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerButton = findViewById(R.id.RecyclerButton);

        RecyclerButton.setOnClickListener(v -> {
            Intent intentRecycler = new Intent(context, CreateRecyclerView.class);
            context.startActivity(intentRecycler);
        });

        SocketButton = findViewById(R.id.SocketButton);

        SocketButton.setOnClickListener(v->{
            Intent intentSocket = new Intent(context, SocketsConnexion.class);
            context.startActivity(intentSocket);
        });

        /*
        // Socket
        chatTextView = findViewById(R.id.Chat);

        socketManager = new SocketManager();
        socketManager.connectSocket();
        socketManager.setOnMessageReceivedListener(message -> runOnUiThread(() -> {
            chatTextView.append(message + "\n"); // Agrega el mensaje al TextView
        }));

        messageEditText = findViewById(R.id.editTextText);
        sendButton = findViewById(R.id.button2);

        sendButton.setOnClickListener(v -> {
            String message = messageEditText.getText().toString();
            if (!message.isEmpty()) {
                socketManager.sendMessage(message);
                messageEditText.getText().clear();
            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.205.249:3001")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        */

        /*
        // Login
        LoginButton.setOnClickListener(v -> {
            Login.CallLogin(service, Username, UserPassword, TAG, context);
        });
        */
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        socketManager.disconnectSocket();
    }

}
