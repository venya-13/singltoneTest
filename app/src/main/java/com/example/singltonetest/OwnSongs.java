package com.example.singltonetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class OwnSongs extends AppCompatActivity {
    private ListView listViewSong;
    private Button stopOwnSongButton;
    static MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_songs);

        listViewSong = findViewById(R.id.listViewSong);
        stopOwnSongButton = findViewById(R.id.stopOwnSongButton);

        stopOwnSongButton.setVisibility(View.GONE);

        displaySongs();

        if (mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        stopOwnSongButton.setOnClickListener(v ->{
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            stopOwnSongButton.setVisibility(View.GONE);
        });
    }



    public ArrayList<File> findSong(File file){
        ArrayList<File> songList = new ArrayList<>();

        File[] files = file.listFiles();

        for(File singleFile: files){
            if (singleFile.isDirectory() && !singleFile.isHidden()){
                songList.addAll(findSong(singleFile));
            }
            else {
                if (singleFile.getName().endsWith(".mp3") || singleFile.getName().endsWith(".wav")){
                    songList.add(singleFile);
                }
            }
        }
        return songList;
    }

    void displaySongs(){
        final ArrayList<File> mySongs = findSong(Environment.getExternalStorageDirectory());

        String[] items = new String[mySongs.size()];
        for (int i = 0; i < mySongs.size(); i++){
            items[i] = mySongs.get(i).getName().toString();
        }
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listViewSong.setAdapter(myAdapter);

        listViewSong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri uri = Uri.parse(mySongs.get(position).toString());
                mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
                mediaPlayer.start();

                stopOwnSongButton.setVisibility(View.VISIBLE);

            }
        });
    }



}