package com.example.exchangerates.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.exchangerates.R;
import com.example.exchangerates.adapter.AdapterBanks;
import com.example.exchangerates.constant.AppConstants;
import com.example.exchangerates.databinding.ActivityRatesBinding;
import com.example.exchangerates.event.ListItemClickListener;
import com.example.exchangerates.model.rates.Bank;
import com.example.exchangerates.presenter.rates.PresenterRates;
import com.example.exchangerates.presenter.rates.ViewRatesCallback;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

public class ActivityRates extends AppCompatActivity implements ViewRatesCallback, ListItemClickListener<Bank> {
    private static final String TAG = ActivityRates.class.getSimpleName();
    private ActivityRatesBinding mActivityRatesBinding;
    private AdapterBanks mAdapterBanks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityRatesBinding = DataBindingUtil.setContentView(this, R.layout.activity_rates);
        mAdapterBanks = new AdapterBanks(this, this);
        mActivityRatesBinding.recyclerViewRates.setAdapter(mAdapterBanks);
        PresenterRates presenterRates = new PresenterRates(this);
        presenterRates.loadRates(AppConstants.Language.ENGLISH);

    }

    @Override
    public void onError(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onRatesLoaded(final Map<String, Bank> rates) {
        mActivityRatesBinding.recyclerViewRates.setLayoutManager(new LinearLayoutManager(ActivityRates.this));
        mActivityRatesBinding.recyclerViewRates.setHasFixedSize(true);
        final String[] currencies = getCurrencies(rates, rates.keySet().toArray(new String[rates.size()]));
        ArrayAdapter adapter = new ArrayAdapter<>(ActivityRates.this, android.R.layout.simple_spinner_item, currencies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mActivityRatesBinding.spinnerCurrencyType.setAdapter(adapter);
        mActivityRatesBinding.spinnerCurrencyType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mAdapterBanks.setCurrency(currencies[i], rates);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private String[] getCurrencies(Map<String, Bank> rates, String[] ratesId) {
        HashSet<String> stateSet = new HashSet<>();
        for (String aRatesId : ratesId) {
            stateSet.addAll(Arrays.asList(rates.get(aRatesId).getCurrenciesTypes()));
        }

        return stateSet.toArray(new String[stateSet.size()]);
    }


    @Override
    public void onListItemClicked(Bank item) {
        Intent intent = new Intent(ActivityRates.this, ActivityBranches.class);
        intent.putExtra(AppConstants.SerializableKeys.BANKS, item);
        startActivity(intent);
    }
}
