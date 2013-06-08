package ru.nsu.ccfit.terekhov.chat.client;

import ru.nsu.ccfit.terekhov.chat.client.gui.MainFrame;
import ru.nsu.ccfit.terekhov.chat.client.model.XmlServerManager;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                XmlServerManager serverManager = new XmlServerManager();

                MainFrame mainFrame = new MainFrame(serverManager);
                mainFrame.setLocationRelativeTo(null);
                serverManager.setEventReceiver(mainFrame);
                mainFrame.setVisible(true);
            }
        });
    }
}
