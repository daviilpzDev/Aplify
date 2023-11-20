package com.medac.aplify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Proxy;
import java.net.Socket;

public class Sockets extends AppCompatActivity {

    private Socket socket;
    Button Enviar;
    private PrintWriter printWriter;
    private TextView Nombre, Edad, Mensaje;
    int puerto = 8000;
    String mensaje, nombre, edad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sockets);

        Nombre = findViewById(R.id.Nombre);
        Edad = findViewById(R.id.Edad);
        Mensaje = findViewById(R.id.Mensaje);
        Enviar = findViewById(R.id.Enviar);

        Enviar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                mensaje = Mensaje.getText().toString();
                edad = Edad.getText().toString();
                nombre = Nombre.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{

                            socket = new Socket( "192.168.1.133",puerto);
                            printWriter = new PrintWriter(socket.getOutputStream());
                            printWriter.write("nombre: "+ nombre + "" + "Edad: "+ "" + "mensaje: "+ mensaje);
                            //printWriter.write(nombre + edad + mensaje);

                            printWriter.flush();
                            printWriter.close();
                            socket.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }

                    }
                }).start();
                Nombre.setText(null);
                Edad.setText(null);
                Mensaje.setText(null);
            }
        });
    }
}