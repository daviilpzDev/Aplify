package com.medac.aplify;

import android.content.Intent;
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

        // Otros botones

        Respuesta = findViewById(R.id.Respuesta);

        // Configurar listeners para los botones
        Pregunta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarConversacion("Para coger citas, debera dirigirse desde la aplicacion al icono del sitio donde quiere coger la citas, al pulsar, le dirigira hacia las citas disponibles en ese establecimiento junto con las horas disponibles");
            }
        });

        Pregunta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarConversacion("Para cancelar una cita, debera dirigirse al apartado de mis citas, en ese apartado estaran todas las citas que tienes pendientes, pulsara sobre la x en la cita que quiera cancelar");
            }
        });

        Pregunta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarConversacion("¿En que mas puedo ayudarte?");
            }
        });

    }

    private void iniciarConversacion(final String respuesta) {
        // Simular respuesta en un hilo secundario
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Simular una demora en la respuesta (puedes eliminar esto en un caso real)
                try {
                    Thread.sleep(200);
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

    public void initCompletePerfil(View view) {
        Intent intentSecondary = new Intent(this, completePerfilActivity.class);
        startActivity( intentSecondary);
    }




}
