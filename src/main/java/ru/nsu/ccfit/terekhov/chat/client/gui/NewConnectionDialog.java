package ru.nsu.ccfit.terekhov.chat.client.gui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NewConnectionDialog extends JDialog {
    private String serverAddress;
    private int serverPort;

    public NewConnectionDialog(JFrame owner) {
        super(owner, "New connection", true);
        setSize(300, 400);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new MigLayout("wrap 2"));
        setContentPane(mainPanel);

        final JLabel serverLabel =new JLabel("Server address");
        mainPanel.add(serverLabel);
        final JTextField serverField = new JTextField();
        serverField.setText("localhost");
        mainPanel.add(serverField);

        final JLabel serverPort = new JLabel("Server port");
        mainPanel.add(serverPort);
        final JTextField serverPortField = new JTextField();
        serverPortField.setText("9999");
        mainPanel.add(serverPortField);


        JButton okButton = new JButton("ok");
        JButton cancelButton = new JButton("cancel");

        mainPanel.add(okButton);
        mainPanel.add(cancelButton);

        okButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewConnectionDialog.this.serverAddress = serverField.getText();
                NewConnectionDialog.this.serverPort = Integer.parseInt(serverPortField.getText());
                NewConnectionDialog.this.dispose();
            }
        });
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public int getServerPort() {
        return serverPort;
    }
}
