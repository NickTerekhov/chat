package ru.nsu.ccfit.terekhov.chat.client.gui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class MessageArea extends JPanel {
    private final JTextArea textArea = new JTextArea();

    public MessageArea() {
        setLayout(new MigLayout("wrap 1"));
        TitledBorder border = BorderFactory.createTitledBorder(
                "messages");
        setBorder(border);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        add(scroll, "width 100%, height 100%");

    }

    public void displayMessage(String user, String message) {
        textArea.append(String.format("%s: %s\n", user, message));
    }
}
