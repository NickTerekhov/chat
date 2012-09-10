package ru.nsu.ccfit.terekhov.chat.client.gui;

import ru.nsu.ccfit.terekhov.chat.client.model.EnterResult;
import ru.nsu.ccfit.terekhov.chat.client.model.ServerManager;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.ListCommand;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.LoginCommand;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.LogoutCommand;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Answer;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;
import ru.nsu.ccfit.terekhov.chat.common.response.response.ErrorAnswer;
import ru.nsu.ccfit.terekhov.chat.common.response.response.SessionSuccessAnswer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class MainFrame extends JFrame {
    private final ServerManager serverManager;

    public MainFrame(ServerManager serverManager) {
        this.serverManager = serverManager;
        setSize(800, 600);
        setTitle("Chat client");
        createMenu();

    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem menuItem = new JMenuItem("New connection");
        fileMenu.add(menuItem);

        menuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewConnectionDialog connectionDialog = new NewConnectionDialog(MainFrame.this);
                connectionDialog.setVisible(true);
                String server = connectionDialog.getServerAddress();
                int serverPort = connectionDialog.getServerPort();
                try {
                    serverManager.connect(server, serverPort);
                    boolean acceptedUserCredentials = false;
                    while (!acceptedUserCredentials) {
                        UserNameDialog userNameDialog = new UserNameDialog(MainFrame.this);
                        userNameDialog.setVisible(true);
                        String userName = userNameDialog.getUserName();
                        EnterResult enterResult = serverManager.tryEnter(userName);
                        if (enterResult.isSucceed()) {
                            acceptedUserCredentials = true;
                        } else {
                            JOptionPane.showMessageDialog(MainFrame.this,
                                    "Cant enter",
                                    "Inane error",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                    }

                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Error connect to server",
                            "Inane error",
                            JOptionPane.ERROR_MESSAGE);
                    try {
                        serverManager.close();
                    } catch (IOException e2) {

                    }
                }
            }
        });

        this.setJMenuBar(menuBar);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    serverManager.logout();
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } finally {
                    System.exit(0);
                }
            }
        });
    }
}
