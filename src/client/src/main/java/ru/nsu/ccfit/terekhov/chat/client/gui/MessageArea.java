package ru.nsu.ccfit.terekhov.chat.client.gui;

import javax.swing.*;

public class MessageArea extends JTextArea {

    public void displayMessage(String user, String message) {
        append(String.format("%s: %s\n", user, message));
    }
}
