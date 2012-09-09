package ru.nsu.ccfit.terekhov.chat;

public class Main
{
	public static void main(String[] args) {
		Server server = new Server();
        Thread serverThread = new Thread(server);
        serverThread.start();

        System.out.println("Server started");
	}
}
