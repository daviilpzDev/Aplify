package com.medac.aplify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthRegistrar;
import com.google.firebase.auth.FirebaseUser;

public class register_activity extends AppCompatActivity {

    // Variables
    private FirebaseAuth mAuth;
    Button btn_register;
    EditText email, name, surname, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // Metodo para bloquear pantalla en vertical
        setContentView(R.layout.activity_register);

        // Inicializamos variables
        mAuth = FirebaseAuth.getInstance();
        btn_register = findViewById(R.id.buttonRegister);
        name = findViewById(R.id.editTextName);
        surname = findViewById(R.id.editTextApellido);
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameUser = name.getText().toString().trim();
                String surNameUser = surname.getText().toString().trim();
                String emailUser = email.getText().toString().trim();
                String passwordUser = password.getText().toString().trim();

                if(nameUser.isEmpty() && surNameUser.isEmpty() && emailUser.isEmpty() && passwordUser.isEmpty()){
                    Toast.makeText(register_activity.this, "Completa los campos", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(register_activity.this, "Registro satisfactorio", Toast.LENGTH_SHORT).show();
                }

            }
        });{


        }



    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }




    // Llamada de vuelta al main
    public void goToMainActivity(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    // Ver condiciones y terminos
    public void initConditions(View view) {
        Intent intent = new Intent(this, privacity.class);
        startActivity(intent);
    }


}