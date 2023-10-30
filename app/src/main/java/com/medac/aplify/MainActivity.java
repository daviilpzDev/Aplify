package com.medac.aplify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore firestore;

    @Override
    // Inicio de app metodo onCreate
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // Metodo para bloquear pantalla en vertical
        setContentView(R.layout.activity_main);


        FirebaseFirestore Firestore = FirebaseFirestore.getInstance();
         Map<String,Object> user = new HashMap<>();
         user.put("Firstname", "Easy");
         user.put("Lastname", "Tuto");
         user.put("description", "Subscribe");

         firestore.collection("users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
             @Override
             public void onSuccess(DocumentReference documentReference) {
                 Toast.makeText(getApplicationContext(),"Succes",Toast.LENGTH_LONG).show();
             }
         }).addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
                 Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_LONG).show();
             }
         });
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