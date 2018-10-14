package com.example.exchangerates.presenter.rates;

import android.support.annotation.NonNull;

import com.example.exchangerates.api.ApiService;
import com.example.exchangerates.model.rates.Bank;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterRates {

    private ViewRatesCallback viewRatesCallback;

    public PresenterRates(ViewRatesCallback viewRatesCallback) {
        this.viewRatesCallback = viewRatesCallback;
    }

    public void loadRates(String lang) {
        Call<Map<String, Bank>> ratesCall = ApiService.getInstance().getRates(lang);
        ratesCall.enqueue(new Callback<Map<String, Bank>>() {
            @Override
            public void onResponse(@NonNull Call<Map<String, Bank>> call, @NonNull Response<Map<String, Bank>> response) {
                viewRatesCallback.onRatesLoaded(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Map<String, Bank>> call, @NonNull Throwable t) {
                viewRatesCallback.onError(t.getMessage());
            }
        });
    }
}
