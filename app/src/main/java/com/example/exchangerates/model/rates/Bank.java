package com.example.exchangerates.model.rates;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Map;

public class Bank implements Serializable {

    @SerializedName("title")
    private String title;

    @SerializedName("date")
    private Integer date;

    @SerializedName("logo")
    private String logo;

    @SerializedName("list")
    Map<String, Map<String, Currency>> list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Map<String, Map<String, Currency>> getList() {
        return list;
    }

    public void setList(Map<String, Map<String, Currency>> list) {
        this.list = list;
    }

    public String[] getCurrenciesTypes() {
        return list.keySet().toArray(new String[list.size()]);
    }
}
