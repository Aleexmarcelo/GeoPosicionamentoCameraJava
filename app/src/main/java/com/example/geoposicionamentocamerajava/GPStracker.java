package com.example.geoposicionamentocamerajava;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class GPStracker implements LocationListener {
    Context context;
    // Declara uma variável do tipo contexto
    public GPStracker(Context c){
        context = c;
    }
    // Construtor de classe, recebendo contexto como parâmetro
    public Location getLocation() {
    //checa as permissões de localização do android
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
    //se a permissão não foi concedida, retorna
            Toast.makeText(context, "Não foi permitido", Toast.LENGTH_SHORT).show();
            return null;
        }
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        //Se a localização estiver habilitada, envia os ultimos dados de latitude e longitude
        if (isGPSEnabled){
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        } else {
//Responsável por enviar a mensagem dizendo para o usuário ligar a localização
            Toast.makeText(context, "Por favor, habilitar o GPS!", Toast.LENGTH_LONG).show();
        }
//retorna ao começo do método
        return null;
    }
    //Sobreescreve o null no return do metodo
    @Override
    public void onProviderDisabled(@NonNull String provider) { }
    //Sobreescreve o null no return do metodo
    @Override
    public void onLocationChanged(@NonNull Location location) { }
    //Sobreescreve a int do metodo
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) { }
}
