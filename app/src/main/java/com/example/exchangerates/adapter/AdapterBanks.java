package com.example.exchangerates.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.exchangerates.R;
import com.example.exchangerates.constant.AppConstants;
import com.example.exchangerates.databinding.ListHeaderCurrencyBinding;
import com.example.exchangerates.databinding.ListItemBanksBinding;
import com.example.exchangerates.event.ListItemClickListener;
import com.example.exchangerates.model.rates.Bank;
import com.example.exchangerates.model.rates.Currency;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdapterBanks extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Bank> mBanks;
    private final ListItemClickListener<Bank> mListItemClickListener;
    private Context mContext;
    private String mCurrency;

    public AdapterBanks(Context context, ListItemClickListener<Bank> listItemClickListener) {
        this.mContext = context;
        this.mListItemClickListener = listItemClickListener;
    }

    public void setCurrency(String currency, Map<String, Bank> rates) {
        mBanks = new ArrayList<>(rates.values());
        List<Bank> banksFiltered = new ArrayList<>();
        this.mCurrency = currency;
        for (int i = 0; i < mBanks.size(); i++) {
            Map<String, Currency> currencyMap = mBanks.get(i).getList().get(this.mCurrency);
            if (currencyMap != null && currencyMap.get(AppConstants.CurrenciesTypes.CURRENT) != null) {
                banksFiltered.add(mBanks.get(i));
            }
        }
        mBanks = new ArrayList<>();
        mBanks.addAll(banksFiltered);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == AppConstants.ListItemTypes.ITEM) {
            final ListItemBanksBinding listItemDiagnosticsInformationBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_banks, parent, false);
            return new AdapterBanks.ViewHolderBanks(listItemDiagnosticsInformationBinding);
        } else if (viewType == AppConstants.ListItemTypes.HEADER) {
            final ListHeaderCurrencyBinding listItemDiagnosticsInformationBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_header_currency, parent, false);
            return new AdapterBanks.ViewHeader(listItemDiagnosticsInformationBinding);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ViewHeader) return;
        
            ViewHolderBanks viewHolderBanks = (ViewHolderBanks) viewHolder;
            Bank bank = mBanks.get(position - 1);
            viewHolderBanks.binding.setBankName(bank.getTitle());
            Map<String, Currency> currencyMap = bank.getList().get(mCurrency);
            Currency currencyCurrent = currencyMap.get(AppConstants.CurrenciesTypes.CURRENT);
            if (currencyCurrent != null) {
                viewHolderBanks.binding.setBuy(currencyCurrent.getBuy());
                viewHolderBanks.binding.setSell(currencyCurrent.getSell());
            }
      
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return AppConstants.ListItemTypes.HEADER;
        return AppConstants.ListItemTypes.ITEM;
    }

    @Override
    public int getItemCount() {
        if (mBanks == null) return 0;
        return mBanks.size() + 1;
    }

    class ViewHolderBanks extends RecyclerView.ViewHolder {
        private final ListItemBanksBinding binding;

        ViewHolderBanks(final ListItemBanksBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
            itemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListItemClickListener.onListItemClicked(mBanks.get(getAdapterPosition() - 1));
                }
            });
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
