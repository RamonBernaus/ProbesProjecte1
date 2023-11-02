package com.example.probes_projecte_1.Recycler;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/api/getProducts")
    Call<List<YourDataModel>> getData();
}

