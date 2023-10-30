package com.example.probes_projecte_1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SocketManager socketManager;
    private TextView chatTextView;
    private EditText messageEditText;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        chatTextView = findViewById(R.id.Chat);

        socketManager = new SocketManager();
        socketManager.connectSocket();
        socketManager.setOnMessageReceivedListener(message -> runOnUiThread(new Runnable() {
            @Override
            public void run() {
                chatTextView.append(message + "\n"); // Agrega el mensaje al TextView
            }
        }));

        messageEditText = findViewById(R.id.editTextText);
        sendButton = findViewById(R.id.button2);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEditText.getText().toString();
                if (!message.isEmpty()) {
                    socketManager.sendMessage(message);
                    messageEditText.getText().clear();
                }
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.205.249:3001")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<BooleanResponse> call = service.validarLogin("admin", "Admin1234");
        call.enqueue(new Callback<BooleanResponse>() {
            @Override
            public void onResponse(@NonNull Call<BooleanResponse> call, @NonNull Response<BooleanResponse> response) {
                if (response.isSuccessful()) {
                    BooleanResponse booleanResponse = response.body();
                    if (booleanResponse != null) {
                        boolean resultado = booleanResponse.isBoolean();
                        Log.d(TAG, "El resultado de la validación de inicio de sesión es: " + resultado);
                    }
                } else {
                    Log.e(TAG, "Error en la solicitud");
                }
            }


            @Override
            public void onFailure(@NonNull Call<BooleanResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "Error en la solicitud: " + t.getMessage());
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        socketManager.disconnectSocket();
    }
}
