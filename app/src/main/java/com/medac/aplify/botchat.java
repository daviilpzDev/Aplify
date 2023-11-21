package com.medac.aplify;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class botchat extends AppCompatActivity {

    private TextView Respuesta;
    private Button Pregunta1;
    private Button Pregunta2;
    private Button Pregunta3;
    private Button Pregunta4;
    private Button Pregunta5;
    private Button Pregunta6;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botchat);

        handler = new Handler(Looper.getMainLooper());

        // Obtener referencias de los elementos de la interfaz
        Pregunta1 = findViewById(R.id.Pregunta1);
        Pregunta2 = findViewById(R.id.Pregunta2);
        Pregunta3 = findViewById(R.id.Pregunta3);
        Pregunta4 = findViewById(R.id.Pregunta4);
        Pregunta5 = findViewById(R.id.Pregunta5);
        Pregunta6 = findViewById(R.id.Pregunta6);
        // Otros botones

        Respuesta = findViewById(R.id.Respuesta);

        // Configurar listeners para los botones
        Pregunta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarConversacion("Respuesta a Pregunta 1");
            }
        });

        Pregunta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarConversacion("Respuesta a Pregunta 2");
            }
        });

        Pregunta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarConversacion("Respuesta a Pregunta 3");
            }
        });
        Pregunta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarConversacion("Respuesta a Pregunta 4");
            }
        });
        Pregunta5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarConversacion("Respuesta a Pregunta 5");
            }
        });
        Pregunta6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarConversacion("Respuesta a Pregunta 6");
            }
        });
        // Configurar otros listeners para los demás botones
    }

    private void iniciarConversacion(final String respuesta) {
        // Simular respuesta en un hilo secundario
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Simular una demora en la respuesta (puedes eliminar esto en un caso real)
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Actualizar la interfaz de usuario en el hilo principal
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mostrarRespuesta(respuesta);
                    }
                });
            }
        }).start();
    }

    private void mostrarRespuesta(String respuesta) {
        Respuesta.setText(respuesta);
    }
}
