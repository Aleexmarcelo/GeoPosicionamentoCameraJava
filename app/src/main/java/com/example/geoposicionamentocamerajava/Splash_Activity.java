package com.example.geoposicionamentocamerajava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash_Activity extends AppCompatActivity {

    //Cria uma instancia com timer para ser usada em um tempo determinado, e final, para que ela nao seja alterada
    private final Timer timer = new Timer();
    TimerTask timerTask;

    //Define o layout que estou usando em activity splash
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Após um determinado tempo rodando uma acão, volta para mainactivity
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        gotoMainActivity();
                    }
                });
            }
        };
        //define o tempo para mostrar a primeira tela do aplicativo, no caso, delay de 4 segundos
        timer.schedule(timerTask, 4000);
    }

    //Inicia uma nova atividade com new task, no caso se o app ja estiver sido usado, ele recomeça
    private void gotoMainActivity()
    {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}