package com.izax.prueba.Util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Funciones {

    public void mensaje(Activity activity, String texto){

        Toast.makeText(activity,
                texto, Toast.LENGTH_LONG).show();

    }

    public void go(Activity activity, Class activity_go){

        Intent intent = new Intent(activity,activity_go);
        activity.startActivity(intent);

    }

    public void GPSPermiso(Activity activity){
        LocationManager locationManager = (LocationManager)  activity.getSystemService(Context.LOCATION_SERVICE);

        if (locationManager != null) {

            boolean isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            if (!isGpsEnabled) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
                dialog.setMessage("Acivar GPS para continuar");
                dialog.setPositiveButton("Activar", (d, which) -> {
                    Intent intentGPS = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    activity.startActivity(intentGPS);
                });
                dialog.setNegativeButton("No", (d, which) -> d.dismiss());
                dialog.show();
            } else {
                //tu codigo
            }

        }
    }

    public void CamaraPermiso(Activity activity){
        // La App esta en ejecución
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Explicamos porque necesitamos el permiso
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.CAMERA)) {

                // Acá continuamos el procesos deseado a hacer

            } else {

                // El usuario no necesitas explicación, puedes solicitar el permiso:
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.CAMERA},
                        2);

                //
            }
        }
    }

}
