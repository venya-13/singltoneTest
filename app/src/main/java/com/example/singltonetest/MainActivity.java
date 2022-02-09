package com.example.singltonetest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private Button internetUrl, bSong1, bSong2, mySongsButton, goToRecord, checkPermissionButton;
    private EditText search;
    private int songNumber = 0;
    private static final int permissionsRequestCodeRecord = 777;
    private static final int permissionsRequestCodeOwnMusic = 666;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        internetUrl = findViewById(R.id.internetUrl);
        search = findViewById(R.id.search);
        bSong1 = findViewById(R.id.bSong1);
        bSong2 = findViewById(R.id.bSong2);
        mySongsButton = findViewById(R.id.mySongsButton);
        goToRecord = findViewById(R.id.goToRecord);
        checkPermissionButton = findViewById(R.id.checkPermissionButton);

        goToRecord.setVisibility(View.GONE);
        mySongsButton.setVisibility(View.GONE);

        permissionsCheckOrRequestRecording();

        checkPermissionButton.setOnClickListener(v -> {
            permissionsCheckOrRequestRecording();

        });

        goToRecord.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Record.class);                // go to record
            startActivity(intent);
        });


        mySongsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, OwnSongs.class);         //   my songs button
            startActivity(intent);
        });

        internetUrl.setOnClickListener(v -> {
            Singltone.getInstance().setSomething(search.getText().toString());                       //   get Url
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);         //
            startActivity(intent);
        });

        bSong1.setOnClickListener(v -> {
            songNumber = 1;                                                                     //
            Singltone.getInstance().setNumber(songNumber);                                      //  get local music 1
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);    //
            startActivity(intent);
        });

        bSong2.setOnClickListener(v -> {
            songNumber = 2;                                                                     //
            Singltone.getInstance().setNumber(songNumber);                                      //  get local music 2
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);    //
            startActivity(intent);
        });
    }

    private void permissionsCheckOrRequestRecording() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            goToRecord.setVisibility(View.VISIBLE);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, permissionsRequestCodeRecord);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            mySongsButton.setVisibility(View.VISIBLE);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, permissionsRequestCodeOwnMusic);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case permissionsRequestCodeRecord:

                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "You need permission, you can click on permission button", Toast.LENGTH_LONG).show();
                    } else{
                        goToRecord.setVisibility(View.VISIBLE);
                    }
                }
                return;

            case permissionsRequestCodeOwnMusic:

                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "You need permission, you can click on permission button", Toast.LENGTH_LONG).show();
                    } else{
                        mySongsButton.setVisibility(View.VISIBLE);
                    }
                }
                return;
        }

    }

}


