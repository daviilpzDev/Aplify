package com.medac.aplify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.util.Log;


import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class registerActivity extends AppCompatActivity {

    // Variables
    private FirebaseAuth mAuth;
    FirebaseFirestore mFirestore;
    private Button btn_register;
    private EditText editTextTextEmailAddress, editTextName, editTextApellido, editTextTextPassword;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // Metodo para bloquear pantalla en vertical
        setContentView(R.layout.activity_register);

        // Inicializamos variables
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        mProgressDialog = new ProgressDialog(registerActivity.this);
        btn_register = findViewById(R.id.buttonRegister);
        editTextName = findViewById(R.id.editTextName);
        editTextApellido = findViewById(R.id.editTextApellido);
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            currentUser.reload();
        }
    }


    private void registerUser() {

        final String nameUser = editTextName.getText().toString().trim();
        final String surNameUser = editTextApellido.getText().toString().trim();
        String emailUser = editTextTextEmailAddress.getText().toString().trim();
        String passwordUser = editTextTextPassword.getText().toString().trim();

        if (nameUser.isEmpty() && surNameUser.isEmpty() && emailUser.isEmpty() && passwordUser.isEmpty()) {
            Toast.makeText(registerActivity.this, "Completa los campos", Toast.LENGTH_SHORT).show();
        } else {
            // mostrar progress dialog
            mProgressDialog.setTitle("Proceso de registro");
            mProgressDialog.setMessage("Registrando usuario, espere un momento.");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();

            // registrar usuario
            mAuth.createUserWithEmailAndPassword(emailUser, passwordUser)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            String id = mAuth.getCurrentUser().getUid();

                            Map<String, Object> map = new HashMap<>();
                            map.put("id", id);
                            map.put("name", nameUser);
                            map.put("surname", surNameUser);
                            map.put("email", emailUser);
                            map.put("password", passwordUser);

                            mFirestore.collection("user").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    finish();
                                    Toast.makeText(registerActivity.this, "Usuario registrado correctamente ", Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                    mProgressDialog.dismiss();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(registerActivity.this, "Error al guardar", Toast.LENGTH_SHORT).show();
                                }
                            });







                        }
        }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(registerActivity.this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                            mProgressDialog.dismiss();
                        }
                    });
    }

}

    // redirige a login
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            // El usuario está autenticado y registrado con éxito
            // Redirigir al usuario a la actividad de inicio de sesión (LoginActivity)
            Intent intent = new Intent(registerActivity.this, loginActivity.class);
            startActivity(intent);
            finish(); // Cierra la actividad actual para que el usuario no pueda regresar presionando el botón "Atrás"
        }
    }


    // Llamada de vuelta al main
    public void goToMainActivity(View view) {
        Intent intent = new Intent(this, loginActivity.class);
        startActivity(intent);
    }

    // Ver condiciones y terminos
    public void initConditions(View view) {
        Intent intent = new Intent(this, privacityActivity.class);
        startActivity(intent);
    }


}