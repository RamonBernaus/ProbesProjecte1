package com.example.probes_projecte_1;

import com.example.probes_projecte_1.Recycler.YourDataModel;

//import BooleanResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    /*
    @GET("/api/validacioLogin")
    Call<BooleanResponse> validarLogin(
            @Query("nom") String nom,
            @Query("contrasenya") String contrasenya
    );*/

    @GET("/api/getProducts")
    Call<List<YourDataModel>> getData();
}
