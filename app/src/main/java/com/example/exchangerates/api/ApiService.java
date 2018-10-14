package com.example.exchangerates.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    public static final String BASE_URL = "http://rate.am";
    private static ApiServiceInterface apiService;

    public static ApiServiceInterface getInstance(){
        if(apiService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiService =  retrofit.create(ApiServiceInterface.class);
        }
        return  apiService;
    }
}
