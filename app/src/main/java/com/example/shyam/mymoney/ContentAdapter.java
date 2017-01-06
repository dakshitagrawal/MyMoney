package com.example.shyam.mymoney;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by shyam on 21-Dec-16.
 */

public class ContentAdapter extends RecyclerView.Adapter<com.example.shyam.mymoney.ContentAdapter.ItemViewHolder> {

        private Context mContext;
        private Cursor mCursor;

        public ContentAdapter(Context context, Cursor cursor) {
            mContext = context;
            mCursor = cursor;
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            int LayoutIDForText = R.layout.expenditure_detail_items_for_recyclerview;
            LayoutInflater inflater = LayoutInflater.from(context);

            View view = inflater.inflate(LayoutIDForText, parent, false);
            ItemViewHolder viewHolder = new ItemViewHolder(view);
            return viewHolder;
        }

    @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            if(!mCursor.moveToPosition(position)){
                return;
            }

            String date = mCursor.getString(mCursor.getColumnIndex(MyMoneyDatabaseContract.ExpenditureEntries.COLUMN_DATE));
            holder.date.setText(date);

            String reason = mCursor.getString(mCursor.getColumnIndex(MyMoneyDatabaseContract.ExpenditureEntries.COLUMN_REASON));
            holder.reason.setText(reason);

            String amountSpent = mCursor.getString(mCursor.getColumnIndex(MyMoneyDatabaseContract.ExpenditureEntries.COLUMN_AMOUNTSPENT));
            holder.amountSpent.setText("\u20B9" +amountSpent);

            String addNote = mCursor.getString(mCursor.getColumnIndex(MyMoneyDatabaseContract.ExpenditureEntries.COLUMN_ADDNOTE));


            String location = mCursor.getString(mCursor.getColumnIndex(MyMoneyDatabaseContract.ExpenditureEntries.COLUMN_LOCATION));
            if(!location.equals("")){
                holder.location.setVisibility(View.VISIBLE);
            }

            String reciept = mCursor.getString(mCursor.getColumnIndex(MyMoneyDatabaseContract.ExpenditureEntries.COLUMN_RECIEPT));
            if(!reciept.equals("")){
                holder.reciept.setVisibility(View.VISIBLE);
            }

            String category = mCursor.getString(mCursor.getColumnIndex(MyMoneyDatabaseContract.ExpenditureEntries.COLUMN_CATEGORY));
            if(category.equals("Bank")){
                holder.category.setImageResource(R.drawable.expenditure_recycler_view_category_bank_icon);
            }else if(category.equals("Entertainment")){
                holder.category.setImageResource(R.drawable.expenditure_recycler_view_category_entertainment_icon);
            }else if(category.equals("Food & Drinks")){
                holder.category.setImageResource(R.drawable.expenditure_recycler_view_category_food_drinks_icon);
            }else if(category.equals("Health")){
                holder.category.setImageResource(R.drawable.expenditure_recycler_view_category_health_icon);
            }else if(category.equals("Shopping")){
                holder.category.setImageResource(R.drawable.expenditure_recycler_view_category_shopping_icon);
            }else if(category.equals("Travel")){
                holder.category.setImageResource(R.drawable.expenditure_recycler_view_category_travelling_icon);
            }else if(category.equals("Other")){
                holder.category.setImageResource(R.drawable.expenditure_recycler_view_category_other_icon);
            }

            holder.bind(reason,amountSpent,date,category,reciept,location,addNote);

        }

        @Override
        public int getItemCount() {
            return mCursor.getCount();
        }

        public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            public final TextView date;
            public final TextView reason;
            public final TextView amountSpent;
            public final ImageView location;
            public final ImageView reciept;
            public final ImageView category;
            public String reasonString;
            public String amountSpentString;
            public String dateString;
            public String categoryString;
            public String recieptString;
            public String locationString;
            public String addNoteString;
            public EditText dateEdit;
            public EditText reasonEdit;
            public EditText amountSpentEdit;
            public EditText locationEdit;
            public EditText recieptEdit;
            public EditText categoryEdit;
            public EditText addNoteEdit;
            public TransactionsDetailsFragment transactionsDetailsFragment;
            public Bundle args;

            public ItemViewHolder(View view) {
                super(view);
                view.setOnClickListener(this);
                date = (TextView) view.findViewById(R.id.expenditureDetailItemDate);
                reason = (TextView) view.findViewById(R.id.expenditureDetailItemReason);
                amountSpent = (TextView) view.findViewById(R.id.expenditureDetailItemAmountSpent);
                location = (ImageView) view.findViewById(R.id.expenditureDetailItemLocation);
                reciept = (ImageView) view.findViewById(R.id.expenditureDetailItemReciept);
                category = (ImageView) view.findViewById(R.id.expenditureDetailItemCategory);
                dateEdit = (EditText) view.findViewById(R.id.dateEdit);
                reasonEdit = (EditText) view.findViewById(R.id.reasonEdit);
                amountSpentEdit = (EditText) view.findViewById(R.id.amountSpentEdit);
                categoryEdit = (EditText) view.findViewById(R.id.categoryEdit);
                recieptEdit = (EditText) view.findViewById(R.id.recieptEdit);
                locationEdit = (EditText) view.findViewById(R.id.locationEdit);
                addNoteEdit = (EditText) view.findViewById(R.id.addNoteEdit);
            }

            public void bind(String reason, String amountSpent, String date, String category, String reciept, String location, String addNote){
                reasonString = reason;
                amountSpentString = amountSpent;
                dateString = date;
                categoryString =category;
                recieptString = reciept;
                locationString = location;
                addNoteString = addNote;
            }

            @Override
            public void onClick(View v) {
                String[] details = new String[]{reasonString, amountSpentString, dateString, categoryString, recieptString, locationString, addNoteString};
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                transactionsDetailsFragment = new TransactionsDetailsFragment();
                args = new Bundle();
                args.putStringArray("TransactionDetails",details );
                transactionsDetailsFragment.setArguments(args);
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.containerView, transactionsDetailsFragment).addToBackStack(null).commit();
            }
        }
}
