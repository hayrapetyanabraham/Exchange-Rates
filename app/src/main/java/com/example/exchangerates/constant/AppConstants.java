package com.example.exchangerates.constant;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface AppConstants {

    @IntDef({ListItemTypes.ITEM, ListItemTypes.HEADER})
    @Retention(RetentionPolicy.SOURCE)
    @interface ListItemTypes {
        int ITEM = 0;
        int HEADER = 1;
    }

    @StringDef({SerializableKeys.BANKS, SerializableKeys.BANKS_ID})
    @Retention(RetentionPolicy.SOURCE)
    @interface SerializableKeys {
        String BANKS = "BANKS";
        String BANKS_ID = "BANKS_ID";
    }

    @StringDef({Language.ARMENIAN, Language.ENGLISH, Language.RUSSIAN})
    @Retention(RetentionPolicy.SOURCE)
    @interface Language {
        String ARMENIAN = "am";
        String ENGLISH = "en";
        String RUSSIAN = "ru";
    }

    @StringDef({CurrenciesTypes.CURRENT, CurrenciesTypes.LAST})
    @Retention(RetentionPolicy.SOURCE)
    @interface CurrenciesTypes {
        String CURRENT = "1";
        String LAST = "0";
    }
}
