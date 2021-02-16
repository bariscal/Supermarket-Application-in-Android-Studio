package com.example.assignment_02;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

/*
this class is custom array adapter. I did not use ready code but I took advantage
from this article:
https://medium.com/@eminuluyol/listview-i%C3%A7in-custom-adapter-yaz%C4%B1m%C4%B1-ve-kullan%C4%B1m%C4%B1-a3727684a7eb
 */

public class DataArrayAdapter extends ArrayAdapter<Data> {

    //inflater object
    private LayoutInflater inflater;
    //Data.class object
    private Data[] datas;

    //constructor method of class
    public DataArrayAdapter(Context context, List<Data> itemList) {
        super(context, R.layout.listview_checkbox, R.id.list_view_item_text, itemList);
        //cache the LayoutInflate to avoid asking for a new one each time.
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Items to display
        Data data = this.getItem(position);

        //child views in each row.
        CheckBox checkBox ;
        TextView textView ;

        //this statement creates a new row view.
        if ( convertView == null ) {
            convertView = inflater.inflate(R.layout.listview_checkbox, null);

            //initializing textview and checkboxes which are listview_checkbox.xml file
            textView = (TextView) convertView.findViewById( R.id.list_view_item_text );
            checkBox = (CheckBox) convertView.findViewById( R.id.list_view_item_checkbox );

            //tag the row with it's child views.
            convertView.setTag( new DataViewHolder(textView,checkBox) );

            //if checkBox is toggled, update the data it is tagged with.
            checkBox.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v ;
                    Data data = (Data) cb.getTag();
                    data.setChecked( cb.isChecked() );
                }
            });
        }
        //reusing existing row view
        else {
            //I used a ViewHolder, so I must avoid having to call findViewById().
            DataViewHolder viewHolder = (DataViewHolder) convertView.getTag();
            checkBox = viewHolder.getCheckBox() ;
            textView = viewHolder.getTextView() ;
        }

        //tag the checkbox with the data it is displaying. with this
        // statement, application can access the data in onClick()
        // when the checkbox is toggled.
        checkBox.setTag( data );

        //this part displays checked datas in textview which is in tab3
        checkBox.setChecked(data.isChecked());
        textView.setText("ID: " + data.getItemID() + " Name: " +  data.getItemName() + " Price: " + data.getItemPrice() + " Stock: " + data.getItemStock()  );

        return convertView;

    }

    //this object returns datas.
    public Object onRetainNonConfigurationInstance() {
        return datas;
    }




}