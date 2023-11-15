package com.medac.aplify;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class servidor {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345); // Puerto del servidor

        System.out.println("Servidor esperando conexiones...");

        Socket clientSocket = serverSocket.accept(); // Esperar a que el cliente se conecte
        System.out.println("Cliente conectado.");

        // Flujo de salida al cliente
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        // Flujo de entrada desde el cliente
        Scanner in = new Scanner(clientSocket.getInputStream());

        // Leer mensajes del cliente y enviar respuestas
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = in.nextLine();
            System.out.println("Cliente: " + input);

            System.out.print("Responder: ");
            String response = scanner.nextLine();
            out.println(response);
        }
    }
}
