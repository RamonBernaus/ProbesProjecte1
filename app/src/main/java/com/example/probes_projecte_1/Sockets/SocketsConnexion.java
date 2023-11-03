package com.example.probes_projecte_1.Sockets;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.probes_projecte_1.R;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketsConnexion extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sockets_probes);

        Button EnviarComanda = findViewById(R.id.EnviarComanda);

        EnviarComanda.setOnClickListener(v->{
            // Al enviar la comanda inicializa el socket
            try {
                Socket socket = IO.socket("http://192.168.205.249:3001"); // Reemplaza con la URL de tu servidor
                socket.connect();

                // Aquí puedes enviar un estado específico al servidor
                String estado = "Enviada";
                socket.emit("comandaStatus", estado);

                // Escucha el evento "comandaResponse"
                socket.on("comandaResponse", args -> {
                    String message = (String) args[0];
                    // Aquí puedes manejar la respuesta de la comanda
                    runOnUiThread(() -> {
                        // Muestra un Toast con el mensaje recibido
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    });
                });

                socket.on("comandaAcceptada", args -> {
                    String message = (String) args[0];
                    // Aquí puedes manejar la respuesta de la comanda
                    runOnUiThread(() -> {
                        // Muestra un Toast con el mensaje recibido
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    });
                });
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });

    }

}
