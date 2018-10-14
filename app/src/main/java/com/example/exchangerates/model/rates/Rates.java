package com.example.exchangerates.model.rates;

import java.io.Serializable;
import java.util.Map;

public class Rates implements Serializable {

    Map<String, Bank> banks;

    public Map<String, Bank> getBanks() {
        return banks;
    }

    public void setBanks(Map<String, Bank> banks) {
        this.banks = banks;
    }

}
