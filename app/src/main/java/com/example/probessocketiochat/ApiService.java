package com.example.probessocketiochat;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiService {


    @GET("/api/validacioLogin")
    Call<BooleanResponse> validarLogin(
            @Query("nom") String nom,
            @Query("contrasenya") String contrasenya
    );
}

