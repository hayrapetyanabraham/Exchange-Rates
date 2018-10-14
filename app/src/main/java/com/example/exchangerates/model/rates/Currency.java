package com.example.exchangerates.model.rates;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Currency implements  Serializable {

    @SerializedName("buy")
    private double buy;

    @SerializedName("sell")
    private double sell;

    public double getBuy() {
        return buy;
    }
    public void setBuy(double buy) {
        this.buy = buy;
    }

    public double getSell() {
        return sell;
    }

    public void setSell(double sell) {
        this.sell = sell;
    }
}
