package ru.nsu.ccfit.terekhov.chat.client.gui;

import net.miginfocom.swing.MigLayout;
import ru.nsu.ccfit.terekhov.chat.client.model.EnterResult;
import ru.nsu.ccfit.terekhov.chat.client.model.ReceiverErrorHandler;
import ru.nsu.ccfit.terekhov.chat.client.model.ServerManager;
import ru.nsu.ccfit.terekhov.chat.client.model.events.EventReceiver;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.ListCommand;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.LoginCommand;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.LogoutCommand;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.MessageCommand;
import ru.nsu.ccfit.terekhov.chat.common.commands.common.Command;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Answer;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;
import ru.nsu.ccfit.terekhov.chat.common.response.response.ErrorAnswer;
import ru.nsu.ccfit.terekhov.chat.common.response.response.SessionSuccessAnswer;
import ru.nsu.ccfit.terekhov.chat.common.response.response.UserListAnswer;
import ru.nsu.ccfit.terekhov.chat.common.transfer.common.UserInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

public class MainFrame extends JFrame implements EventReceiver {
    private final ServerManager serverManager;
    private MessageArea messageArea;
    private UserListPanel userListPanel;
    private SendArea inputMessageTextarea;
    private JButton sendMessageButton;
    private ReceiverErrorHandler errorHandler;

    public MainFrame(ServerManager serverManager) {
        this.serverManager = serverManager;

        setSize(700, 550);
        setTitle("Chat client");
        createLayout();
        enabledSendControls(false);
        createMenu();

        errorHandler = new ReceiverErrorHandler() {
            @Override
            public void processError(IOException e) {
                JOptionPane.showMessageDialog(MainFrame.this, "Disconnect from server", "Error", JOptionPane.ERROR_MESSAGE);
                enabledSendControls(false);
            }
        };

    }

    private void createLayout() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new MigLayout());
        setContentPane(mainPanel);
        messageArea = new MessageArea();
        mainPanel.add(messageArea, "width 400, height 300");
        userListPanel = new UserListPanel();
        mainPanel.add(userListPanel, "width 200, height 300, wrap");
        inputMessageTextarea = new SendArea();
        mainPanel.add(inputMessageTextarea, "width 400, height 100, wrap");

        sendMessageButton = new JButton("Send message");
        addSendHandler(sendMessageButton);
        mainPanel.add(sendMessageButton);

    }

    private void addSendHandler(JButton sendMessageButton) {
        sendMessageButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = inputMessageTextarea.getText();
                if (!message.isEmpty()) {
                    try {
                        if (userListPanel.getCurrentUser().equals("All")) {
                            serverManager.sendMessage(message);
                        } else {
                            serverManager.sendPrivateMessage(message, userListPanel.getCurrentUser());
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
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
                            setTitle(getTitle() + " - " + userName);
                        } else {
                            JOptionPane.showMessageDialog(MainFrame.this,
                                    "Cant enter",
                                    "Inane error",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                    }


                        serverManager.getResponseReceiver().addErrorHandler(errorHandler);

                        serverManager.requestUserList();
                        UserListAnswer userListAnswer = (UserListAnswer) serverManager.getReadedAnswer();
                        List<UserInfo> userList = userListAnswer.getUsers();
                        userListPanel.setUsers(userList);
                        enabledSendControls(true);

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

        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);
        exitItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    serverManager.getResponseReceiver().removeHandler(errorHandler);
                    serverManager.logout();
                } catch (IOException e1) {

                } finally {
                    System.exit(0);
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    serverManager.getResponseReceiver().removeHandler(errorHandler);
                    serverManager.logout();
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }  finally {
                    System.exit(0);
                }
            }
        });

        this.setJMenuBar(menuBar);


    }

    private void enabledSendControls(boolean enabled) {
        sendMessageButton.setEnabled(enabled);
    }

    @Override
    public void displayMessage(String user, String message) {
        messageArea.displayMessage(user, message);
    }

    @Override
    public void userLogin(String userName) {
        userListPanel.addUser(userName);
    }

    @Override
    public void userLogout(String userName) {
        userListPanel.removeUser(userName);
    }
}
