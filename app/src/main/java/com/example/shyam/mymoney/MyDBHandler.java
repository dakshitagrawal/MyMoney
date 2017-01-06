package com.example.shyam.mymoney;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by shyam on 13-Dec-16.
 */

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Transactions.db";

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS", "database created/opened");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String query_create = "CREATE TABLE " + MyMoneyDatabaseContract.ExpenditureEntries.TABLE_NAME + " (" +
                MyMoneyDatabaseContract.ExpenditureEntries._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                MyMoneyDatabaseContract.ExpenditureEntries.COLUMN_REASON + " TEXT, "+
                MyMoneyDatabaseContract.ExpenditureEntries.COLUMN_AMOUNTSPENT + " TEXT, "+
                MyMoneyDatabaseContract.ExpenditureEntries.COLUMN_DATE + " TEXT, "+
                MyMoneyDatabaseContract.ExpenditureEntries.COLUMN_CATEGORY + " TEXT, "+
                MyMoneyDatabaseContract.ExpenditureEntries.COLUMN_RECIEPT + " TEXT, "+
                MyMoneyDatabaseContract.ExpenditureEntries.COLUMN_LOCATION + " TEXT, "+
                MyMoneyDatabaseContract.ExpenditureEntries.COLUMN_ADDNOTE + " TEXT);";

        db.execSQL(query_create);
        Log.e("DATABASE OPERATIONS", "table is created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MyMoneyDatabaseContract.ExpenditureEntries.TABLE_NAME);
        onCreate(db);
    }

    public void addExpenditureDetail(ExpenditureDetailsforDatabase expenditureDetailsforDatabase){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyMoneyDatabaseContract.ExpenditureEntries.COLUMN_REASON, expenditureDetailsforDatabase.get_reason());
        values.put(MyMoneyDatabaseContract.ExpenditureEntries.COLUMN_AMOUNTSPENT, expenditureDetailsforDatabase.get_amountSpent());
        values.put(MyMoneyDatabaseContract.ExpenditureEntries.COLUMN_DATE, expenditureDetailsforDatabase.get_date());
        values.put(MyMoneyDatabaseContract.ExpenditureEntries.COLUMN_CATEGORY, expenditureDetailsforDatabase.get_category());
        values.put(MyMoneyDatabaseContract.ExpenditureEntries.COLUMN_LOCATION, expenditureDetailsforDatabase.get_location());
        values.put(MyMoneyDatabaseContract.ExpenditureEntries.COLUMN_RECIEPT, expenditureDetailsforDatabase.get_reciept());
        values.put(MyMoneyDatabaseContract.ExpenditureEntries.COLUMN_ADDNOTE, expenditureDetailsforDatabase.get_addNote());
        db.insert(MyMoneyDatabaseContract.ExpenditureEntries.TABLE_NAME, null, values);
        Log.e("DATABASE OPERATIONS" , "details added");
    }
}
