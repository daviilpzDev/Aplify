package com.medac.aplify;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class cliente {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345); // Conectar al servidor

        // Flujo de salida al servidor
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Flujo de entrada desde el servidor
        Scanner in = new Scanner(socket.getInputStream());

        // Leer mensajes del servidor y enviar mensajes
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Mensaje: ");
            String message = scanner.nextLine();
            out.println(message);

            String response = in.nextLine();
            System.out.println("Servidor: " + response);
        }
    }
}
