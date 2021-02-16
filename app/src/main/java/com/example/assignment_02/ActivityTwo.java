package com.example.assignment_02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class ActivityTwo extends AppCompatActivity {

    //defining objects
    TextView amount, time;
    Button exit, submit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_layout);

        //title of the Project
        setTitle("116200045 - Barış Çal - Assignment 2");

        //initializing objects
        amount = findViewById(R.id.amount);
        time = findViewById(R.id.date);
        exit = findViewById(R.id.last_exit);
        submit = findViewById(R.id.submit);

        //Intent and bundle objects
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        //this values takes values from MainActivity.java with the key "TOTAL"
        double total = bundle.getDouble("TOTAL");

        //taking system time
        //Date date = new Date();
        Date date = new Date();

        //taking values from other class
        bundle.putDouble("TOTAL", total);
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK, intent);


        //putting total payment to intent
        amount.setText("AMOUNT: " + total);
        //putting system time to intent
        time.setText("DATE: " + systemDate("yyyy/MM/dd H:mm:ss"));

        //submit buttons Listener which is in Intent and payment_layout.xml
        //this buttons finishes intent. Actually it does not have an duty like
        //send data to server etc. It is symbolic.
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //exit buttons Listener which is in Intent and payment_layout.xml
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //HELPER METHOD
                exit(v);
            }
        });

    }

    //HELPER METHODS
    //this method kills application
    public void exit(View v){
        System.exit(0);
    }


    public String systemDate(String format)
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(cal.getTime());
    }
}
