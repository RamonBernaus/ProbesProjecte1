package com.example.probes_projecte_1.Recycler;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.probes_projecte_1.ApiService;
import com.example.probes_projecte_1.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateRecyclerView extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_view);

        recyclerView = findViewById(R.id.Products);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.205.249:3001") // Reemplaza con tu dirección IP o dominio
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        CreateRecycler(service, recyclerView);

    }

    public void CreateRecycler(ApiService service, RecyclerView recyclerView){
        Call<List<YourDataModel>> call = service.getData();

        call.enqueue(new Callback<List<YourDataModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<YourDataModel>> call, @NonNull Response<List<YourDataModel>> response) {
                if (response.isSuccessful()) {
                    List<YourDataModel> data = response.body();
                    adapter = new MyAdapter(data, CreateRecyclerView.this);
                    recyclerView.setAdapter(adapter);
                } else {
                    // Manejar la respuesta no exitosa aquí
                    Log.d("TAG", "No va la resposta");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<YourDataModel>> call, @NonNull Throwable t) {
                // Manejar errores de la solicitud aquí
                Log.e("Error", "No va la solicitud");
            }
        });
    }
}

