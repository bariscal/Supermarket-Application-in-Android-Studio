package com.example.assignment_02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import android.view.View.OnClickListener;


public class MainActivity extends AppCompatActivity implements Deprecated {

    //activity_main
    public  TabHost tabHost;

    //main_activity_1
    public EditText itemID, itemName, itemPrice, itemStock;
    public Button insert, reset, exit, generate;

    //main_activity_2
    public ListView listView;
    public Button basket, basket_generated, reset_datas;

    //main_activity_3
    public TextView tab3_textView;
    public Button button_pay, button_reset, button_exit;
    public ListView tab3_listView;

    //listview_checkbox
    public CheckBox checkBox;
    public TextView textView;

    //Data class objects
    public Data[] datas ;
    public Data[] generatedDatas;

    //array adapters
    public ArrayAdapter<Data> listAdapter ;
    public ArrayAdapter<Data> listAdapter2 ;
    public ArrayAdapter<Data> listAdapter5 ;
    public ArrayAdapter<Data> listAdapter_generated ;

    /*
    ArrayLists for datas.
    dataList is for inserted datas.
    checkedDataList is for checked datas that in the second tab's listView.
    generatedDataList is for generated datas. I created 17 different objects to
    add listview which is in tab2. You can use GENERATE button which is in tab1
    and you can see how application works. also you can add datas manually with
    using TextViews.
     */
    ArrayList<Data> dataList = new ArrayList<Data>();
    ArrayList<Data> checkedDataList = new ArrayList<Data>();
    ArrayList<Data> generatedList = new ArrayList<Data>();

    Context context;
    //total price which is on tab3.
    double total = 0;
    double new_total;
    int basket_count = 1;
    double payment_value;

    //double new_price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Title for Project
        setTitle("116200045 - Barış Çal - Assignment 2");

        //initialize main_tab_1
        itemID = findViewById(R.id.itemID);
        itemName = findViewById(R.id.itemName);
        itemPrice = findViewById(R.id.itemPrice);

        itemStock = findViewById(R.id.itemStock);

        insert = findViewById(R.id.insert);
        reset = findViewById(R.id.reset);
        exit = findViewById(R.id.exit);
        generate = findViewById(R.id.generate);

        //initialize main_tab_2
        listView = findViewById(R.id.listView);
        basket = findViewById(R.id.button_add);
        basket_generated = findViewById(R.id.button_add_generated);
        reset_datas = findViewById(R.id.reset_datas);

        //initialize main_tab_3
        tab3_textView = findViewById(R.id.tab3_textView);
        button_pay = findViewById(R.id.button_pay);
        button_reset = findViewById(R.id.button_reset);
        button_exit = findViewById(R.id.button_exit);

        //initialize listview_checkbox
        checkBox = findViewById(R.id.list_view_item_checkbox);
        textView = findViewById(R.id.list_view_item_text);
        tab3_listView = findViewById(R.id.tab3_listView);

        //tabhost initialization
        tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup();
        TabHost.TabSpec tabSpec;

        //3 tabspecs for each tab
        tabSpec = tabHost.newTabSpec("Screen-1");
        tabSpec.setContent(R.id.tab1);
        tabSpec.setIndicator("SHOP", null);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("Screen-2");
        tabSpec.setContent(R.id.tab2);
        tabSpec.setIndicator("STOCK", null);
        tabHost.addTab(tabSpec);


        tabSpec = tabHost.newTabSpec("Screen-3");
        tabSpec.setContent(R.id.tab3);
        tabSpec.setIndicator("RECEIPT", null);
        tabHost.addTab(tabSpec);

        //first tab will be main_tab_1
        tabHost.setCurrentTab(0);


        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

            }
        });


        //insert button Listener which is in main_tab_1
        insert.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //HELPER METHOD
                insert(v);
            }
        });

        //reset button Listener which is in main_tab_1
        reset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //HELPER METHOD
                reset(v);
            }
        });

        //exit button Listener which is in main_tab_1
        exit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //HELPER METHOD
                exit(v);
            }
        });

        /*
        I WILL EXPLAIN HELPER METHODS IN THE BELOW
         */

        // When item is tapped, toggle checked properties of CheckBox and Data.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView<?> parent, View item,
                                     int position, long id) {
                Data data = listAdapter.getItem( position );
                data.toggleChecked();
                DataViewHolder viewHolder = (DataViewHolder) item.getTag();
                viewHolder.getCheckBox().setChecked(data.isChecked());

            }
        });




        //add to basket button Listener which is in main_tab_2
        basket.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //HELPER METHOD
                calculate(v);
                /*
                this code pair calculates total price. I assigned checked values to
                checkedDataList. new_price takes selected objects price. total is the
                final value. and in the last line I showed total amount in the
                textView which is is main_tab_3.
                 */
                if (v.getId() == basket.getId()) {
                    basket_count = basket_count-1;
                    double new_price = 0;
                    for (int j = 0; j < checkedDataList.size(); j++) {
                       // Data data = checkedDataList.get(j);
                        //if (data.isChecked()) {
                            new_price += checkedDataList.get(j).getItemPrice();
                            total += new_price;
                            //calculate(v);
                            //tab3_textView.setText(" TOTAL: " + total + "\n");
                        //}


                        Data data = checkedDataList.get(j);
                        if(data.getItemStock() == 0){
                            Toast.makeText(getApplicationContext(), "Out of Stock!",500).show();
                        }


                    }
                    tab3_textView.setText(" TOTAL: " + new_price + "\n");
                    payment_value = new_price;
                    insert_data(v);



                }
            }
        });

        //I created this button for generated datas. Please use this only
        //with generated datas, otherwise datas will be removed.
        basket_generated.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate(v);
                if (v.getId() == basket_generated.getId()) {
                    basket_count = basket_count-1;
                    double new_price = 0;
                    for (int j = 0; j < checkedDataList.size(); j++) {
                        // Data data = checkedDataList.get(j);
                        //if (data.isChecked()) {
                        new_price += checkedDataList.get(j).getItemPrice();
                        total = new_price;
                        //tab3_textView.setText(" TOTAL: " + total + "\n");
                        //}

                        //error message
                        Data data = checkedDataList.get(j);
                        if(data.getItemStock() == 0){
                            Toast.makeText(getApplicationContext(), "out of stock",500).show();
                        }

                    }
                    tab3_textView.setText(" TOTAL: " + new_price + "\n");
                    payment_value = new_price;
                    generate(v);




                }
            }
        });

        /*
         button_reset's Listener which is in main_tab_3. This button resets the list and
         total. It resets listview which is main_tab_3.
        */

        button_reset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                listAdapter2.clear();
                tab3_listView.setAdapter(listAdapter2);
                total = 0;
                tab3_textView.setText(" TOTAL: " + 0 + "\n");
                generate(v);
            }
        });

        //exit buttons Listener which is in main_tab_3. This ends the application.
        button_exit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //HELPER METHOD
                exit(v);
            }
        });

        //generate buttons Listener which is in main_tab_1.
        generate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //HELPER METHOD
                generate(v);
            }
        });

        //pay buttons Listener which is in main_tab_3. This button
        //starts intent. (I tried to make 3D-Secure page.)
        button_pay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //HELPER METHOD
                payment(v);
            }
        });

        //this button reset datas in listviews.
        reset_datas.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                reset_datas(v);
            }
        });

    }

    // HELPER METHODS

    //This method is using for intent. I post the total data to the intent.
    //operations are in ActivityTwo.java
    public void payment(View v){
        Intent intent = new Intent(MainActivity.this, ActivityTwo.class);
        Bundle bundle = new Bundle();

        bundle.putDouble("TOTAL", payment_value);
        intent.putExtras(bundle);
        startActivity(intent);

        //startActivityForResult(intent, 5000);
    }

    //For intent. I took advantage from week11's lecture.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 5000 && resultCode == Activity.RESULT_OK){
            //get the data from the Bundle
            Bundle bundle = data.getExtras();
            double total = bundle.getDouble("TOTAL");

        }
    }

    public void calculate(View v){
        //getCounts() tooks listAdapters count and data is took Data.class items.
        //if statement checks data isChecked and I added checked datas to
        //checkedDataList. listAdapter2 is using for adding checkedDataList to
        //listview which is in tab3.
        for (int i = 0; i < listAdapter.getCount(); i++)
        {

            Data data = listAdapter.getItem(i);
            //controllin stock
            if (data.getItemStock() > 0) {
                if (data.isChecked()) {

                    int new_stock = data.getItemStock() - 1;
                    data.setItemStock(new_stock);

                    checkedDataList.add(data);

                    //set custom array adapter as the ListView's adapter.
                    listAdapter2 = new DataArrayAdapter(getApplicationContext(), checkedDataList);
                    tab3_listView.setAdapter(listAdapter2);
                }

                //this statement removes not checked datas from list.
                else if (data.isChecked() != true) {
                    checkedDataList.remove(data);
                    listAdapter2 = new DataArrayAdapter(getApplicationContext(), checkedDataList);
                    tab3_listView.setAdapter(listAdapter2);
                    //total = 0;
                }
            }
            //error message
            if (data.getItemStock() == 0) {
                Toast.makeText(getApplicationContext(), "out of stock", 500).show();
            }

        }
        //this part is sets Text which is in tab3. prints result total.
        tab3_textView.setText(" TOTAL: " + total + "\n");
    }

    public void insert(View v){

        if(itemID.getText().toString().trim().equals("")  || itemName.getText().toString().trim().equals("") || itemPrice.getText().toString().trim().equals("") || itemStock.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(), "Fill all fields!", Toast.LENGTH_LONG).show();
        }

        else{
            datas = (Data[]) getLastNonConfigurationInstance();

            //adding information which are taking from user holding in Datas.class array.
            datas = new Data[]{new Data(Integer.parseInt(itemID.getText().toString()), itemName.getText().toString(),
                    Double.parseDouble(itemPrice.getText().toString()), Integer.parseInt(itemStock.getText().toString())),
            };

            //I dont need them but I am keeping them.
            //Integer id = Integer.parseInt(itemID.getText().toString());
            //String name = itemName.getText().toString();
            //Integer price = Integer.parseInt(itemPrice.getText().toString());
            //dataList.add(new Data(id,name,price));

            //Adding objects which are in datas array to ArrayList.
            dataList.addAll(Arrays.asList(datas));

            //set custom array adapter as the ListViews adapter.
            listAdapter = new DataArrayAdapter(this, dataList);
            listView.setAdapter(listAdapter);

            //After insert textViews will be resetted.
            itemID.setText(null);
            itemName.setText(null);
            itemPrice.setText(null);
            itemStock.setText(null);
        }
    }

    //this method reset datas which are in tab2.
    //In assignment file, reset button in tab1 but I used that reset button for
    //reset TextViews.
    public void reset_datas(View v){
        dataList.clear();
        generatedList.clear();
        checkedDataList.clear();
        total = 0;
        tab3_textView.setText("TOTAL: " + total);
        listAdapter2 = new DataArrayAdapter(getApplicationContext(), dataList);
        listAdapter2 = new DataArrayAdapter(getApplicationContext(), generatedList);
        listAdapter2 = new DataArrayAdapter(getApplicationContext(), checkedDataList);
        listView.setAdapter(listAdapter2);
    }


    //reset method for reset button which is in tab1.
    public void reset(View v){
        itemID.setText(null);
        itemName.setText(null);
        itemPrice.setText(null);
        itemStock.setText(null);
    }

    //exit method for exit buttons in the application.
    //this method kills the process.
    public void exit(View v){
        System.exit(0);
    }

    //I generated datas for ease of use. If you click generate button this datas will be
    //added to listview and you can select among them.
    public void generate(View v){

        Random r = new Random();
        generatedDatas = new Data[]{new Data(01, "chocolate", 1, r.nextInt(10)), new Data(02, "chocolate", 3,r.nextInt(10)),
                new Data(03, "chocolate", 6,r.nextInt(10)), new Data(04, "chocolate", 1,r.nextInt(10)), new Data(05, "chocolate", 8,r.nextInt(10)),
                new Data(06, "chocolate", 5,r.nextInt(10)), new Data(07, "chocolate", 1), new Data(8, "chocolate", 17,r.nextInt(10)),
                new Data(9, "chocolate", 7,r.nextInt(10)), new Data(10, "chocolate", 1), new Data(11, "chocolate", 7,r.nextInt(10)),
                new Data(12, "chocolate", 3,r.nextInt(10)), new Data(13, "chocolate", 1), new Data(14, "chocolate", 9,r.nextInt(10)),
                new Data(15, "chocolate", 2,r.nextInt(10)), new Data(16, "chocolate", 1), new Data(17, "chocolate", 18,r.nextInt(10))
        };

        //I added this this datas to generatedDatasList
        generatedList.addAll(Arrays.asList(generatedDatas));

        //listAdapter for generatedDatasList.
        listAdapter = new DataArrayAdapter(this, generatedList);
        //adding datas to listview which is in tab2.
        listView.setAdapter(listAdapter);


    }

    //this method is using for show renewed list.
    public void insert_data(View v){

        //Adding objects which are in datas array to ArrayList.
        //dataList.addAll(Arrays.asList(datas));

        //set custom array adapter as the ListViews adapter.
        listAdapter5 = new DataArrayAdapter(this, dataList);
        listView.setAdapter(listAdapter5);


    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }


}