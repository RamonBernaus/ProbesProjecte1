package com.example.probessocketiochat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {
    public String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtiene la referencia al EditText
        EditText Username = findViewById(R.id.Username);
        EditText UserPassword = findViewById(R.id.UserPassword);

        // Obtiene el texto ingresado por el usuario
        String UsernameText = Username.getText().toString();
        String UserPasswordText = UserPassword.getText().toString();

        Button Login = findViewById(R.id.Login);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.2.2.83:3001")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiService service = retrofit.create(ApiService.class);

        Login.setOnClickListener(v -> CallLogin(service, TAG, Username, UserPassword));
    }

    public static void CallLogin(ApiService service, String TAG, EditText Username, EditText UserPassword) {
        // Obtiene el texto ingresado por el usuario
        String UsernameText = Username.getText().toString();
        String UserPasswordText = UserPassword.getText().toString();

        Call<BooleanResponse> call = service.validarLogin(UsernameText, UserPasswordText);

        String url = call.request().url().toString();
        Log.d(TAG, "URL de la solicitud: " + url);

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
}

