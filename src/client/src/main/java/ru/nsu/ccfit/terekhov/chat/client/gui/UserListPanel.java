package ru.nsu.ccfit.terekhov.chat.client.gui;


import net.miginfocom.swing.MigLayout;
import ru.nsu.ccfit.terekhov.chat.common.transfer.common.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class UserListPanel extends JPanel {
    private final List<String> users = new ArrayList<String>();
    private final JComboBox<String> userSelector = new JComboBox<String>();

    public UserListPanel() {
        setLayout(new MigLayout("wrap 1"));
        setBorder(BorderFactory.createTitledBorder("User list"));
        paintUsers();
    }

    public void setUsers(List<UserInfo> userList) {
        for( UserInfo userInfo : userList ) {
            users.add(userInfo.getUserName());
        }
        paintUsers();
    }

    private void paintUsers() {
        removeAll();
        for( String user : users) {
            final JLabel userLabel = new JLabel(user);
            add(userLabel);
        }
       userSelector.removeAllItems();
        userSelector.addItem("All");
        for( String user : users) {
            userSelector.addItem(user);
        }

        JPanel userSelectorPanel = new JPanel();
        userSelectorPanel.setLayout(new MigLayout());
        userSelectorPanel.add(new JLabel("Select user"));
        userSelectorPanel.add(userSelector);
        add(userSelectorPanel);

        revalidate();
        repaint();
    }

    public void addUser(String userName) {
        users.add(userName);
        paintUsers();
    }

    public String getCurrentUser() {
        return (String) userSelector.getModel().getSelectedItem();
    }

    public void removeUser(String userName) {
        users.remove(userName);
        paintUsers();
    }
}
