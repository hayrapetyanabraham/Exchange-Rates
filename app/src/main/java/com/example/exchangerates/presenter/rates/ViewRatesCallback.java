package com.example.exchangerates.presenter.rates;

import com.example.exchangerates.model.rates.Bank;
import com.example.exchangerates.presenter.error.ErrorCallback;

import java.util.Map;

public interface ViewRatesCallback extends ErrorCallback {

    void onRatesLoaded(Map<String, Bank> rates);

}
