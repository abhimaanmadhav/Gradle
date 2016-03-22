package com.Abhimaan.myapplication.backend;

/** The object model for the data we are sending through endpoints */
public class MyBean {

    private String myData;

    public String getData() {
        return myData;
    }

    @Override
    public String toString()
        {
            return "MyBean{" +
                    "myData='" + myData + '\'' +
                    '}';
        }

    public void setData(String data) {
        myData = data;
    }
}