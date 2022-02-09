package com.example.singltonetest;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class Singltone extends MainActivity {
    private String Something;
    private int Number;

    private Singltone(){

    }

    private static Singltone instance;

    public static Singltone getInstance(){
        if(instance == null){
            instance = new Singltone();
        }
        return instance;
    }

    public void setSomething (String param){
        Something = param;

    }

    public String getSomething (){

        return Something;
    }

    public void setNumber (int param){
        Number = param;

    }

    public int getNumber (){

        return Number;
    }


}
