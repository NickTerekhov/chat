package ru.nsu.ccfit.terekhov.chat.server;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Server server = null;
    private static Thread serverThread = null;

    public static void main(String[] args) throws IOException, InterruptedException {
          startServer();




    }

    private static void startServer() throws IOException {
        System.out.println("Starting server");
        server = new Server();
        serverThread = new Thread(server);
        serverThread.start();
        System.out.println("Server started");
    }


}
