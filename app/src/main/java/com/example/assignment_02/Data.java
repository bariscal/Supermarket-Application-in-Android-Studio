package com.example.assignment_02;

public class Data {

    //attributes of Data Object: ID, Name and Price as wanted in Assignment sheet.
    //I could add stock attribute but I had to make more operations in code, so I
    //submit it like this. There is a boolean value which is controls Data is checked
    //or not.
    public int itemID = 0;
    public String itemName = "" ;
    public double itemPrice = 0.0;
    public int itemStock;
    public boolean checked = false ;

    //Constructors.
    public Data(Data data) {}

    public Data( String name ) {
        this.itemName = name ;
    }

    public Data(int id, String name, double price){
        this.itemID = id;
        this.itemName = name;
        this.itemPrice = price;
    }

    public Data(int id, String name, double price, int stock){
        this.itemID = id;
        this.itemName = name;
        this.itemPrice = price;
        this.itemStock = stock;
    }

    public Data( String name, boolean checked ) {
        this.itemName = name ;
        this.checked = checked ;
    }

    //getter-setter methods for each attribute.
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String name) {
        this.itemName = name;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemStock() {
        return itemStock;
    }

    public void setItemStock(int itemStock) {
        this.itemStock = itemStock;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    //toString() method for print the attributes information.
    public String toString() {
        return "ID: " + itemID + " Name: " + itemName + " Price: " + itemPrice + " Stock: " + itemStock;
    }

    //controls checked or not checked
    public void toggleChecked() {
        checked = !checked ;
    }

}
