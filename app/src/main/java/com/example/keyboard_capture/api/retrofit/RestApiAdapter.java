package com.example.keyboard_capture.api.retrofit;


import com.example.keyboard_capture.api.Constants;
import com.example.keyboard_capture.api.client.Service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Juan on 31/10/2017.
 */

public class RestApiAdapter {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)).build();
    //public String dir = root.getROOT_URL();
    public Service getClientService() {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(Constants.ROOT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return  retrofit.create(Service.class);
    }
}
