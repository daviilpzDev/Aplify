package com.medac.aplify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    // Inicio de app metodo onCreate
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // Metodo para bloquear pantalla en vertical
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

    }



    // Llamada a activity registro
    public void initSecAct(View view){

        Intent intentSecondary = new Intent(this, register_activity.class);
        startActivity( intentSecondary);
    }

    // Llamada a activity forgetPassword
    public void initOlvido(View view){

        Intent intentSecondary = new Intent(this, forgetPasword_activity.class);
        startActivity( intentSecondary);
    }




}