package com.example.shyam.mymoney;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpenditureFragment extends Fragment {
    private ContentAdapter mContentAdapter;
    private RecyclerView mNumbersList;
    private SQLiteDatabase db;

    public ExpenditureFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expenditure, container, false);
        mNumbersList = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mNumbersList.setLayoutManager(layoutManager);
        MyDBHandler myDBHandler = new MyDBHandler(getContext());
        db = myDBHandler.getWritableDatabase();

        Cursor cursor = getAllExpenditureDetails();
        mContentAdapter = new ContentAdapter(getContext(),cursor );
        mNumbersList.setAdapter(mContentAdapter);


        FloatingActionButton FAB_addExpenditure = (FloatingActionButton) view.findViewById(R.id.FAB_addExpenditure);
        FAB_addExpenditure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.containerView,new TransactionsDetailsFragment()).addToBackStack(null).commit();
            }
        });


        return view;
    }

    private Cursor getAllExpenditureDetails(){
        return db.query(MyMoneyDatabaseContract.ExpenditureEntries.TABLE_NAME,null,null,null,null,null,MyMoneyDatabaseContract.ExpenditureEntries.COLUMN_DATE + " DESC ");
    }
}


