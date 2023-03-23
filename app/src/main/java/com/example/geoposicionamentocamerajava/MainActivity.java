package com.example.geoposicionamentocamerajava;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Cria uma exibição de imagem
    private ImageView imageViewFoto;
    private Button btnGeo;

    //Executa o que há de layout em main, gerando botoes e imagens
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Responsal por geral o botao de gps no app e checa se tem permissao de localizacao
        btnGeo = (Button) findViewById(R.id.btn_gps);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
//Responsavel por detectar que o usuario clicou no botao gps
        btnGeo.setOnClickListener(new View.OnClickListener() {
            //Quando o usuario clicar, pega informaçoes de localizacao
            @Override
            public void onClick(View view) {
                GPStracker g = new GPStracker(getApplication());
                Location l = g.getLocation();
//Printa toda informaçao da localizacao na tela do app
                if (l != null) {
                    double lat = ((Location) l).getLatitude();
                    double lon = l.getLongitude();
                    Toast.makeText(getApplicationContext(), "LATITUDE: " + lat + "\n LONGITUDE: " + lon, Toast.LENGTH_LONG).show();
                }
            }
        });
//Checa se tem permissao para usar a camera, se sim, abre a camera, se nao, pede para o usuario aprovar a permissao
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);
        }
//após clicar no botao de tirar foto, guarda a imagem
        imageViewFoto = (ImageView) findViewById(R.id.image_foto);
        findViewById(R.id.btn_pic).setOnClickListener(new View.OnClickListener() {
            //Após tirar a foto, usa override para substituir a foto existente, e exibe ela na tela main
            @Override
            public void onClick(View view) {
                tirarFoto();
            }
        });
    }

    //Guarda a foto do app
    private void tirarFoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    //Usa override para substituir as informacoes e fotos
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//checa os codigos de permissao, se for ok, guarda as informações de data, hora e etc, cria um nome para a foto
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");
            imageViewFoto.setImageBitmap(imagem);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}