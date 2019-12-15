package com.example.testapp.model.network;

import com.example.testapp.model.NetworkData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
@GET("/s/2iodh4vg0eortkl/facts.json/")
Call<NetworkData> getData();
}
