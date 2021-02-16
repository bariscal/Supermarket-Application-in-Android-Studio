package com.example.assignment_02;

import android.widget.CheckBox;
import android.widget.TextView;

//this class is using for the objects that are is listview_checkbox.xml
//this class holds the information which are in listviews.

public class DataViewHolder {

    //defining objects
    public CheckBox checkBox ;
    public TextView textView ;

    //constructors.
    public DataViewHolder() {}

    public DataViewHolder( TextView textView, CheckBox checkBox ) {
        this.checkBox = checkBox ;
        this.textView = textView ;
    }


    //getter-setter methods for checkBox and TextView.
    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

}