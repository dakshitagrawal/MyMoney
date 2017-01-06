package com.example.shyam.mymoney;

import android.text.Editable;

/**
 * Created by shyam on 13-Dec-16.
 */

public class ExpenditureDetailsforDatabase {
    private String _reason;
    private String _amountSpent;
    private String _date;
    private String _category;
    private String _reciept;
    private String _location;
    private String _addNote;

    public ExpenditureDetailsforDatabase(){
        //empty parameter constructor
    }

    public ExpenditureDetailsforDatabase(String reason, String amountSpent, String date,
                                         String category, String location, String reciept, String addNote){
        _reason = reason;
        _amountSpent = amountSpent;
        _date = date;
        _category = category;
        _reciept = reciept;
        _location = location;
        _addNote = addNote;
    }

    public void set_reason(String _reason) {
        this._reason = _reason;
    }

    public void set_amountSpent(String _amountSpent) {
        this._amountSpent = _amountSpent;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public void set_category(String _category) {
        this._category = _category;
    }

    public void set_reciept(String _reciept) {
        this._reciept = _reciept;
    }

    public void set_location(String _location) {
        this._location = _location;
    }

    public void set_addNote(String _addNote) {
        this._addNote = _addNote;
    }

    public String get_reason() {
        return _reason;
    }

    public String get_amountSpent() {
        return _amountSpent;
    }

    public String get_date() {
        return _date;
    }

    public String get_category() {
        return _category;
    }

    public String get_reciept() {
        return _reciept;
    }

    public String get_location() {
        return _location;
    }

    public String get_addNote() {
        return _addNote;
    }
}
