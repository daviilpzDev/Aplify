package com.medac.aplify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class completePerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_perfil);
    }

    public void initCompletePerfil(View view) {
        Intent intentSecondary = new Intent(this, completePerfilActivity.class);
        startActivity( intentSecondary);
    }

    public void initChat(View view) {
        Intent intentSecondary = new Intent(this, botchat.class);
        startActivity( intentSecondary);
    }

    public void initHome(View view) {
        Intent intentSecondary = new Intent(this, MainActivity.class);
        startActivity( intentSecondary);
    }

}