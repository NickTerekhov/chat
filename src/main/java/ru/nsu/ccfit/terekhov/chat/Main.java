package ru.nsu.ccfit.terekhov.chat;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Server server = null;
    private static Thread serverThread = null;

    public static void main(String[] args) throws IOException, InterruptedException {


        Scanner scanner = new Scanner(System.in);
        for (; ; ) {
            System.out.println("Enter command:");
            String command = scanner.nextLine();
            if (command.equals("start")) {
                if (server == null) {
                    startServer();
                }
            } else if (command.equals("stop")) {
                stopServer(serverThread);
            } else if (command.equals("exit")) {
                stopServer(serverThread);
                return;
            } else {
                System.out.println("Unknowed command: " + command);
            }
        }


    }

    private static void startServer() throws IOException {
        System.out.println("Starting server");
        server = new Server();
        serverThread = new Thread(server);
        serverThread.start();
        System.out.println("Server started");
    }

    private static void stopServer(Thread serverThread) throws InterruptedException, IOException {
        if (null != server) {
            System.out.println("Stopping server");
            server.interrupt();
            serverThread.join();
            server = null;
            System.out.println("Server stopped");
        }
    }
}
