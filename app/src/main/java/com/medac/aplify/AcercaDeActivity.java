package com.medac.aplify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AcercaDeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);
    }
    public void initMain(View view) {
        Intent intentSecondary = new Intent(this, MainActivity.class);
        startActivity( intentSecondary);
    }

}