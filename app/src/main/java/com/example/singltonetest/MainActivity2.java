package com.example.singltonetest;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity2 extends AppCompatActivity {
    private TextView textView;
    private Button playMedia, stopButton;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = findViewById(R.id.text);
        playMedia = findViewById(R.id.playMedia);
        stopButton = findViewById(R.id.stopButton);

        String myUrl = Singltone.getInstance().getSomething();//instance!!!
        int songNumber = Singltone.getInstance().getNumber();//instance!!!

        textView.setText(myUrl);



        //OWN MUSIC!!!
        if (songNumber == 1) {
            mediaPlayer = MediaPlayer.create(this, R.raw.standart);                          // set music
            playMedia.setOnClickListener(v -> {
                mediaPlayer.start();
            });

            stopButton.setOnClickListener(v -> {
                mediaPlayer.release();
                mediaPlayer = null;
            });
        }

        //OWN MUSIC 2!!!
        if (songNumber == 2) {
            mediaPlayer = MediaPlayer.create(this, R.raw.standart_second);                          // set music 2
            playMedia.setOnClickListener(v ->{
                mediaPlayer.start();
        });

            stopButton.setOnClickListener(v -> {
                mediaPlayer.release();
                mediaPlayer = null;
            });
        }


        if (songNumber == 0) {
            playMedia.setOnClickListener(v -> {
                String url = myUrl;      // -URL- You can take Variable -(url)-
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioAttributes(
                        new AudioAttributes.Builder()
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)     //
                                .setUsage(AudioAttributes.USAGE_MEDIA)                  //
                                .build()                                                // URL music
                );                                                                      //
                                                                                        //
                try {
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepare(); // might take long! (for buffering, etc)
                    mediaPlayer.start();
                } catch (Exception ex) {
                    Log.d("ololo", ex.toString());
                }
            });
        }

    }
}