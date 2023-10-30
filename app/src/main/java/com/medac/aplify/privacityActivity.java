package com.medac.aplify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

public class privacityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacity_activity);
    }

    // Llamada de vuelta al main
    public void initRegister(View view) {
        Intent intent = new Intent(this, registerActivity.class);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // Metodo para bloquear pantalla en vertical
        startActivity(intent);
    }
}