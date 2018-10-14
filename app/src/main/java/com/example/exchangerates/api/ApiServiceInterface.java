package com.example.exchangerates.api;

import com.example.exchangerates.model.rates.Bank;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServiceInterface {

    @GET("/ws/mobile/v2/rates.ashx")
    Call<Map<String, Bank>> getRates(@Query("lang") String lang);

}
