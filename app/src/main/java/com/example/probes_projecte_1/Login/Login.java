package com.example.probes_projecte_1.Login;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.probes_projecte_1.ApiService;
import com.example.probes_projecte_1.SocketsChat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login {
    /*
    public static void CallLogin(ApiService service, EditText Username, EditText UserPassword, String TAG, Context context){
        String UsernameText = Username.getText().toString();
        String UserPasswordText = UserPassword.getText().toString();

        Call<BooleanResponse> call = service.validarLogin(UsernameText, UserPasswordText);
        call.enqueue(new Callback<BooleanResponse>() {
            @Override
            public void onResponse(@NonNull Call<BooleanResponse> call, @NonNull Response<BooleanResponse> response) {
                if (response.isSuccessful()) {
                    BooleanResponse booleanResponse = response.body();
                    if (booleanResponse != null) {
                        boolean resultado = booleanResponse.isBoolean();
                        Log.d(TAG, "El resultado de la validación de inicio de sesión es: " + resultado);
                        if(resultado) {
                            Intent intent = new Intent(context, SocketsChat.class);
                            context.startActivity(intent);
                        }
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
    }*/
}
