package com.example.singltonetest;

import android.net.Uri;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class Singltone extends MainActivity {
    private String Something;
    private int Number;
    private Uri Uri;
    private File File;

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

    public void setUri (Uri param){
        Uri = param;

    }

    public Uri getUri (){

        return Uri;
    }

    public void setFile (File param){
        File = param;

    }

    public File getFile (){

        return File;
    }



}
