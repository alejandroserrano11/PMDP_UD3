package com.example.pmdm_enero;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LocalActivity extends AppCompatActivity {

    private ImageButton localGoesMain;
    private ImageButton playLocal;
    private ImageButton stopLocal;
    private ImageButton pauseLocal;
    private TextView singName;
    private TextView actualTime;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        localGoesMain = (ImageButton) findViewById(R.id.localGoesMain);
        playLocal = (ImageButton) findViewById(R.id.playLocal);
        stopLocal = (ImageButton) findViewById(R.id.stopLocal);
        pauseLocal = (ImageButton) findViewById(R.id.pauseLocal);
        singName = (TextView) findViewById(R.id.singName);
        actualTime = (TextView) findViewById(R.id.actualTimeWeb);


        mediaPlayer = MediaPlayer.create(this,R.raw.musica);


        singName.setText("Nombre de la canción: Still D.R.E. (Instrumental)");

        localGoesMain.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(LocalActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
        );

        playLocal.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mediaPlayer.start();
                        actualTime.setVisibility(View.INVISIBLE);
                    }
                }
        );

        stopLocal.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mediaPlayer.stop();
                        try {
                            mediaPlayer.prepare();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );

        pauseLocal.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mediaPlayer.pause();
                        actualTime.setText("total time: " + formatoTiempo(mediaPlayer.getDuration())
                                + "\n"+formatoTiempo(mediaPlayer.getCurrentPosition()));
                        actualTime.setVisibility(View.VISIBLE);
                    }
                }
        );


    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.stop();
    }

    private String formatoTiempo(int miliSeg){
        int segundos = miliSeg / 1000;
        int minutos = segundos / 60;
        segundos = segundos - ( minutos *60);
        return ("MIN: "+minutos+" / SEC: "+segundos);

    }
}