package com.example.exchangerates.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.exchangerates.R;
import com.example.exchangerates.constant.AppConstants;
import com.example.exchangerates.databinding.ListHeaderCurrencyBinding;
import com.example.exchangerates.databinding.ListItemRatesBinding;
import com.example.exchangerates.model.rates.Currency;

import java.util.Map;

public class AdapterRates extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Map<String, Map<String, Currency>> mCurrencyList;
    private String[] mRatesMapKeys;
    private Context mContext;

    public AdapterRates(Context context, Map<String, Map<String, Currency>> currencyList) {
        this.mContext = context;
        this.mCurrencyList = currencyList;
        this.mRatesMapKeys = this.mCurrencyList.keySet().toArray(new String[currencyList.size()]);

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return AppConstants.ListItemTypes.HEADER;
        return AppConstants.ListItemTypes.ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == AppConstants.ListItemTypes.ITEM) {
            final ListItemRatesBinding listItemDiagnosticsInformationBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_rates, parent, false);
            return new AdapterRates.ViewHolderRates(listItemDiagnosticsInformationBinding);
        } else if (viewType == AppConstants.ListItemTypes.HEADER) {
            final ListHeaderCurrencyBinding listItemDiagnosticsInformationBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_header_currency, parent, false);
            return new ViewHeader(listItemDiagnosticsInformationBinding);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof ViewHeader) {
            ViewHeader viewHeader = (ViewHeader) viewHolder;
            viewHeader.binding.setText(mContext.getString(R.string.distance));
        } else {
            ViewHolderRates viewHolderBanks = (ViewHolderRates) viewHolder;
            Map<String, Currency> currencyMap = mCurrencyList.get(mRatesMapKeys[position - 1]);
            viewHolderBanks.binding.setCurrencyType(mRatesMapKeys[position - 1]);
            Currency currencyCurrent = currencyMap.get(AppConstants.CurrenciesTypes.CURRENT);
            if (currencyCurrent != null) {
                viewHolderBanks.binding.setBuy(currencyCurrent.getBuy());
                viewHolderBanks.binding.setSell(currencyCurrent.getSell());
            }
        }
    }

    @Override
    public int getItemCount() {
        return mCurrencyList.size() + 1;
    }

    class ViewHolderRates extends RecyclerView.ViewHolder {
        private final ListItemRatesBinding binding;

        ViewHolderRates(final ListItemRatesBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }

    class ViewHeader extends RecyclerView.ViewHolder {
        ListHeaderCurrencyBinding binding;

        ViewHeader(final ListHeaderCurrencyBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}
