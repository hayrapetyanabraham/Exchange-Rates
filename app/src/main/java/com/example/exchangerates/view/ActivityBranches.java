package com.example.exchangerates.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.example.exchangerates.R;
import com.example.exchangerates.adapter.AdapterRates;
import com.example.exchangerates.constant.AppConstants;
import com.example.exchangerates.databinding.ActivityBranchesBinding;
import com.example.exchangerates.model.rates.Bank;

public class ActivityBranches extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBranchesBinding mActivityBranchesBinding = DataBindingUtil.setContentView(this, R.layout.activity_branches);
        Intent intent = getIntent();
        Bank bank = (Bank) intent.getSerializableExtra(AppConstants.SerializableKeys.BANKS);
        mActivityBranchesBinding.recyclerViewRates.setLayoutManager(new LinearLayoutManager(ActivityBranches.this));
        mActivityBranchesBinding.recyclerViewRates.setHasFixedSize(true);
        AdapterRates adapterBanks = new AdapterRates(this, bank.getList());
        mActivityBranchesBinding.recyclerViewRates.setAdapter(adapterBanks);
    }

}
