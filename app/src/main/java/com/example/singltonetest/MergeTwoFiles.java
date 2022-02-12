package com.example.singltonetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.SequenceInputStream;

public class MergeTwoFiles extends AppCompatActivity {
    private Button startMerge;
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;
    MediaPlayer mediaPlayer2;
    MediaPlayer mediaPlayer3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge_two_files);

        startMerge = findViewById(R.id.startMerge);
        startMerge.setVisibility(View.GONE);

        Uri uri = Singltone.getInstance().getUri();//instance!!!
        mediaPlayer2 = MediaPlayer.create(getApplicationContext(), uri);

        startMerge.setOnClickListener(v ->{
            ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
            File musicDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
            File file = new File(musicDirectory, "recordingFile" + ".mp3");

        });
    }

    public void startRecordWithMusic (View view){

        try {
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setOutputFile(getRecordingFilePath());
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);
            mediaRecorder.prepare();
            mediaRecorder.start();

            mediaPlayer2.start();

            Toast.makeText(this, "Recording is start", Toast.LENGTH_LONG).show();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }

    }

    public void stopRecordVoice (View view){
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;

        mediaPlayer2.stop();
        mediaPlayer2.release();

        startMerge.setVisibility(View.VISIBLE);

        Toast.makeText(this, "Recording is stop", Toast.LENGTH_LONG).show();
    }

    public void recordPlayerWithMusic (View view){
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(getRecordingFilePath());
            mediaPlayer.prepare();
            mediaPlayer.start();

            Toast.makeText(this, "I play", Toast.LENGTH_LONG).show();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }

    }


    private String getRecordingFilePath(){
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File musicDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file = new File(musicDirectory, "recordingFile" + ".mp3");
        return file.getPath();
    }
}