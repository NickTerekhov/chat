package ru.nsu.ccfit.terekhov.chat.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClient {
    public static void main(String[] args) throws IOException {
        final Socket socket = new Socket("localhost", 9999);
        final InputStream inputStream = socket.getInputStream();
        final OutputStream outputStream = socket.getOutputStream();
        final DataInputStream dataInputStream = new DataInputStream(inputStream);
        final DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        final String command = "<?xml version=\"1.0\"?>" +
                "<command name=\"login\">" +
                "<name>userName</name>" +
                "<type>SimpleClientV01</type>" +
                "</command>";
        sendAndRead(command, dataInputStream, dataOutputStream);

        final String userListCommand = "<?xml version=\"1.0\"?>" +
                "<command name=\"list\">" +
                "<session>12345</session>" +
                "</command>";
        sendAndRead(userListCommand, dataInputStream, dataOutputStream);

        final String logoutCommand = "<?xml version=\"1.0\"?>" +
                "<command name=\"logout\">" +
                "<session>12345</session>" +
                "</command>";
        sendCommand(logoutCommand, dataOutputStream);

        socket.close();
    }

    private static void sendAndRead(String command, DataInputStream dataInputStream, DataOutputStream dataOutputStream) throws IOException {
        sendCommand(command, dataOutputStream);
        String readCmd = readCommand(dataInputStream);


        System.out.println("Readed:");
        System.out.println(readCmd);
    }

    private static String readCommand(DataInputStream dataInputStream) throws IOException {
        int dataSize = dataInputStream.readInt();
        byte[] data = new byte[dataSize];
        dataInputStream.readFully(data);
        String readString = new String(data);
        return readString;

    }

    private static void sendCommand(final String command, DataOutputStream dataOutputStream) throws IOException {
        byte[] commandBytes = command.getBytes();
        dataOutputStream.writeInt(commandBytes.length);
        dataOutputStream.write(commandBytes);
    }
}
