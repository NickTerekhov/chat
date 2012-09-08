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
        byte[] commandBytes = command.getBytes();
        dataOutputStream.writeInt(commandBytes.length);
        dataOutputStream.write(commandBytes);

        // read response
        int dataSize = dataInputStream.readInt();
        byte[] data = new byte[dataSize];
        dataInputStream.readFully(data);
        String readString = new String(data);

        System.out.println("Readed:");
        System.out.println(readString);

        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
