package com.medac.aplify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.CheckBox;
import android.view.View;
import android.widget.Toast;

public class register_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // Metodo para bloquear pantalla en vertical
        setContentView(R.layout.activity_register);

        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox2);
        if (checkBox.isChecked()) {
            checkBox.setChecked(false);
        }
    }


    // Llamada de vuelta al main
    public void goToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // Ver condiciones y terminos
    public void initConditions(View view) {
        Intent intent = new Intent(this, privacity.class);
        startActivity(intent);
    }


}