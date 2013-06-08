package ru.nsu.ccfit.terekhov.chat.client.gui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class SendArea extends JPanel {
    private final JTextArea inputArea = new JTextArea();

    public SendArea() {
        setLayout(new MigLayout());
        setBorder(BorderFactory.createTitledBorder("Enter your message here"));
        JScrollPane scroll = new JScrollPane(inputArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        add(scroll, "width 100%, height 100%");
    }

    public String getText() {
        return inputArea.getText();
    }
}
