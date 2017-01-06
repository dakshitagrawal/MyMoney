package com.example.shyam.mymoney;


import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */

@TargetApi(24)
public class TransactionsDetailsFragment extends Fragment {

    EditText reasonEdit;
    EditText amountSpentEdit;
    EditText dateEdit;
    EditText categoryEdit;
    EditText recieptEdit;
    EditText locationEdit;
    EditText addNoteEdit;
    TextInputLayout recieptTextInputLayout;
    TextInputLayout locationTextInputLayout;
    TextInputLayout addNoteTextInputLayout;
    MyDBHandler myDBHandler;
    Button saveExpenditureDetailButton;
    Switch MoreExpenditureDetailsSwitch;
    Calendar calendar = Calendar.getInstance();
    int dayOfMonth;
    int month;
    String monthname;
    int year;
    int hour;
    int minute;
    String am_pm;
    ListPopupWindow listPopupWindow;
    String[] categoryList = {"Bank", "Entertainment", "Food & Drinks", "Health", "Shopping", "Travel", "Other"};
    int[] icons = {R.drawable.category_icon_bank,R.drawable.category_icon_entertainment,R.drawable.category_icon_food_drinks,
    R.drawable.category_icon_health,R.drawable.category_icon_shopping,R.drawable.category_icon_travelling,R.drawable.category_icon_other};

    public TransactionsDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String[] value = getArguments().getStringArray("TransactionDetails");
        View v = inflater.inflate(R.layout.fragment_transactions_details, container, false);

        reasonEdit = (EditText) v.findViewById(R.id.reasonEdit);
        amountSpentEdit = (EditText) v.findViewById(R.id.amountSpentEdit);
        dateEdit = (EditText) v.findViewById(R.id.dateEdit);
        categoryEdit = (EditText) v.findViewById(R.id.categoryEdit);
        locationEdit = (EditText) v.findViewById(R.id.locationEdit);
        recieptEdit = (EditText) v.findViewById(R.id.recieptEdit);
        addNoteEdit = (EditText) v.findViewById(R.id.addNoteEdit);
        recieptTextInputLayout = (TextInputLayout) v.findViewById(R.id.recieptTextInputLayout);
        locationTextInputLayout = (TextInputLayout) v.findViewById(R.id.locationTextInputLayout);
        addNoteTextInputLayout = (TextInputLayout) v.findViewById(R.id.addNoteTextInputLayout);
        if(value[0]!="") {
            reasonEdit.setText(value[0]);
            amountSpentEdit.setText(value[1]);
            dateEdit.setText(value[2]);
            categoryEdit.setText(value[3]);
            recieptEdit.setText(value[4]);
            locationEdit.setText(value[5]);
            addNoteEdit.setText(value[6]);
        }
        myDBHandler = new MyDBHandler(getActivity());

        MoreExpenditureDetailsSwitch = (Switch) v.findViewById(R.id.moreExpenditureDetailsSwitch);
        MoreExpenditureDetailsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    recieptTextInputLayout.setVisibility(buttonView.VISIBLE);
                    locationTextInputLayout.setVisibility(buttonView.VISIBLE);
                    addNoteTextInputLayout.setVisibility(buttonView.VISIBLE);
                }else{
                    recieptTextInputLayout.setVisibility(buttonView.GONE);
                    locationTextInputLayout.setVisibility(buttonView.GONE);
                    addNoteTextInputLayout.setVisibility(buttonView.GONE);
                }
            }
    });

        saveExpenditureDetailButton = (Button) v.findViewById(R.id.saveExpenditureDetailsButton);
        saveExpenditureDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExpenditureDetailsforDatabase expenditureDetailsforDatabase = new ExpenditureDetailsforDatabase(reasonEdit.getText().toString(),
                        amountSpentEdit.getText().toString(), dateEdit.getText().toString(), categoryEdit.getText().toString(), locationEdit.getText().toString(),
                        recieptEdit.getText().toString(), addNoteEdit.getText().toString());
                myDBHandler.addExpenditureDetail(expenditureDetailsforDatabase);
                Toast.makeText(getContext(), "data added", Toast.LENGTH_LONG).show();
                reasonEdit.setText("");
                amountSpentEdit.setText("");
                dateEdit.setText("");
                categoryEdit.setText("");
                categoryEdit.setCompoundDrawablesWithIntrinsicBounds(getActivity().getResources()
                        .getDrawable(R.drawable.category_icon),null,null,null);
                locationEdit.setText("");
                recieptEdit.setText("");
                addNoteEdit.setText("");
                myDBHandler.close();
            }
        });

         final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year1, int month1, int dayOfMonth1) {
                year = year1;
                month = month1;
                dayOfMonth = dayOfMonth1;
                switch ((month + 1)){
                    case 1:
                        monthname = "Jan";
                        break;
                    case 2:
                        monthname = "Feb";
                        break;
                    case 3:
                        monthname = "Mar";
                        break;
                    case 4:
                        monthname = "Apr";
                        break;
                    case 5:
                        monthname = "May";
                        break;
                    case 6:
                        monthname = "Jun";
                        break;
                    case 7:
                        monthname = "Jul";
                        break;
                    case 8:
                        monthname = "Aug";
                        break;
                    case 9:
                        monthname = "Sep";
                        break;
                    case 10:
                        monthname = "Oct";
                        break;
                    case 11:
                        monthname = "Nov";
                        break;
                    case 12:
                        monthname = "Dec";
                        break;

                }
                updateLabel();
            }
        };

        final TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute1) {
                hour = hourOfDay;
                minute = minute1;
                if(hour/12==0){
                    am_pm = "AM";
                }else if(hour/12==1){
                    am_pm = "PM";
                }
                updateLabel();
            }
        };

        dateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(getContext(), date, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

                new TimePickerDialog(getContext(),time, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), false).show();
            }
        });

        listPopupWindow = new ListPopupWindow(getContext());
        listPopupWindow.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, categoryList));
        listPopupWindow.setAnchorView(categoryEdit);
        listPopupWindow.setModal(true);
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Drawable drawable = getActivity().getResources().getDrawable(icons[position]);
                categoryEdit.setText(categoryList[position]);
                categoryEdit.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
                listPopupWindow.dismiss();
            }
        });

        categoryEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listPopupWindow.show();
            }
        });
        return v;
    }

    public void updateLabel(){
        dateEdit.setText( new StringBuilder().append(dayOfMonth).append(" ").append(monthname).append(", ").append(year).append(" - ")
        .append(hour%12).append(":").append(minute).append(" ").append(am_pm));
    }

}
