package ru.nsu.ccfit.terekhov.chat.client.gui;


import net.miginfocom.swing.MigLayout;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class UserListPanel extends JPanel {
    public UserListPanel() {
        setLayout(new MigLayout("wrap 1"));
        setBackground(Color.RED);
    }

    public void setUsers(List<UserInfo> userList) {
        removeAll();
        for( UserInfo userInfo : userList ) {
            JLabel userLabel = new JLabel(userInfo.getUserName());
            add(userLabel);
        }
    }
}
