package com.example.shyam.mymoney;

import android.provider.BaseColumns;

/**
 * Created by shyam on 20-Dec-16.
 */

public class MyMoneyDatabaseContract {

    public static final class ExpenditureEntries implements BaseColumns{
        public static final String TABLE_NAME = "Expenditure_Details";
        public static final String COLUMN_REASON = "REASON";
        public static final String COLUMN_AMOUNTSPENT = "AMOUNT_SPENT";
        public static final String COLUMN_DATE = "DATE";
        public static final String COLUMN_CATEGORY = "CATEGORY";
        public static final String COLUMN_RECIEPT = "RECIEPT";
        public static final String COLUMN_LOCATION = "LOCATION";
        public static final String COLUMN_ADDNOTE = "ADD_NOTE";

    }
}
