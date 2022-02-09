package com.example.singltonetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class Record extends AppCompatActivity {
    private Button recordButton, stopButton, playButton, checkMicroButton;
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        recordButton = findViewById(R.id.recordButton);
        stopButton = findViewById(R.id.stopButton);
        playButton = findViewById(R.id.playButton);
        checkMicroButton = findViewById(R.id.checkMicroButton);


        checkMicroButton.setOnClickListener(v -> {
            checkMicro();
        });
    }

    public void startRecord (View view){

        try {
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setOutputFile(getRecordingFilePath());
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);
            mediaRecorder.prepare();
            mediaRecorder.start();

            Toast.makeText(this, "Recording is start", Toast.LENGTH_LONG).show();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }

    }

    public void stopRecord (View view){
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;

        Toast.makeText(this, "Recording is stop", Toast.LENGTH_LONG).show();
    }

    public void recordPlayer (View view){
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

    private boolean isMicrophonePresent(){
        if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)){
            return true;
        }
        else{
            return false;
        }
    }

    private void checkMicro (){
        if (isMicrophonePresent() == true){
            Toast.makeText(this, "Micro is true", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Micro is false", Toast.LENGTH_LONG).show();
        }
    }

    private String getRecordingFilePath(){
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File musicDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file = new File(musicDirectory, "recordingFile" + ".mp3");
        return file.getPath();
    }
}