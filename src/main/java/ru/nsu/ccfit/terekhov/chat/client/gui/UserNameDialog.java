package ru.nsu.ccfit.terekhov.chat.client.gui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UserNameDialog extends JDialog {
    private String userName;

    public UserNameDialog(JFrame owner) {
        super(owner, "Enter username", true);
        setSize(300, 400);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new MigLayout("wrap 2"));
        setContentPane(mainPanel);

        JLabel userNameLabel = new JLabel("Enter user name");
        mainPanel.add(userNameLabel);
        final JTextField userNameField = new JTextField("user");
        mainPanel.add(userNameField);

        JButton okButton = new JButton("ok");
        mainPanel.add(okButton);
        okButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserNameDialog.this.userName = userNameField.getText();
                UserNameDialog.this.dispose();
            }
        });
    }

    public String getUserName() {
        return userName;
    }
}
