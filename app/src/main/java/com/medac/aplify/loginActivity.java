package com.medac.aplify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.auth.User;

import java.util.Timer;
import java.util.TimerTask;


public class loginActivity extends AppCompatActivity {

    // Variables
    private FirebaseAuth mAuth;
    private GoogleSignInClient googleSignInClient;
    private FirebaseAuth firebaseAuth;
    private static final int RC_SIGN_IN = 123; // Código de solicitud para iniciar sesión con Google

    EditText email, password;
    Button buttonLogin;
    @Override
    // Inicio de app metodo onCreate
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // Metodo para bloquear pantalla en vertical
        setContentView(R.layout.activity_login);


        // Inicializa varibles
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        // registro de accion de boton de login
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pasa a toString editText
                String emailUser = email.getText().toString().trim();
                String passUser = password.getText().toString().trim();

                if(emailUser.isEmpty() || passUser.isEmpty()){
                    Toast.makeText(loginActivity.this, "Añade los datos correctamente", Toast.LENGTH_SHORT).show();
                }else {
                    // si es correcto hace llamada a metodo loginUser
                    loginUser(emailUser, passUser);
                }
            }
        });


        // Inicializa FirebaseAuth y GoogleSignInOptions
        firebaseAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        // Encuentra la ImageView en tu diseño
        ImageView googleSignInButton = findViewById(R.id.imageView4);

        // Agrega un OnClickListener para la ImageView
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInWithGoogle();
            }
        });
    }

    // Metodo de inicio de sesion
    private void loginUser(String emailUser, String passUser) {
        mAuth.signInWithEmailAndPassword(emailUser, passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    finish();
                    startActivity(new Intent(loginActivity.this, MainActivity.class));
                    Toast.makeText(loginActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(loginActivity.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(loginActivity.this, "Añade los datos correctamente", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Metodos inicio de sesion con google
    private void signInWithGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // El inicio de sesión con Google falló
                e.printStackTrace();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // El inicio de sesión con Google fue exitoso, redirige a privacity activity.
                            startActivity(new Intent(loginActivity.this, MainActivity.class));

                        } else {
                            // El inicio de sesión con Google falló
                            Toast.makeText(loginActivity.this, "Error al iniciar sesión con Google", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }








    // Llamada a activity registro
    public void initSecAct(View view){

        Intent intentSecondary = new Intent(this, registerActivity.class);
        startActivity( intentSecondary);
    }

    // Llamada a activity forgetPassword
    public void initOlvido(View view){

        Intent intentSecondary = new Intent(this, forgetPassActivity.class);
        startActivity( intentSecondary);
    }




}