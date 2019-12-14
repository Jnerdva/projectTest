package com.example.testapp.model;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.testapp.model.network.ApiClient;
import com.example.testapp.model.network.ApiService;

import io.reactivex.Maybe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Repository {
    private static Repository util = null;
    final ApiService apiService;

    private Repository() {
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public static Repository getInstance(Context context) {
        if (util != null) {
            return util;
        }
        util = new Repository();
        return util;
    }

    public LiveData<NetworkData> getAllData() {
        final MutableLiveData<NetworkData> data = new MutableLiveData<>();
        apiService.getData()
                .enqueue(new Callback<NetworkData>() {


                    @Override
                    public void onResponse(Call<NetworkData> call, Response<NetworkData> response) {
                        Log.d(TAG, "onResponse response:: " + response);



                        if (response.body() != null) {
                            data.setValue(response.body());

                            Log.d(TAG, "articles total result:: " + response.body().getTitle());
                        }
                    }

                    @Override
                    public void onFailure(Call<NetworkData> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }

    }

