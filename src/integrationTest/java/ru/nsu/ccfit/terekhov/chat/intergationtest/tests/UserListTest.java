package ru.nsu.ccfit.terekhov.chat.intergationtest.tests;


import org.junit.Test;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.ListCommand;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.LoginCommand;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;
import ru.nsu.ccfit.terekhov.chat.common.response.response.SessionSuccessAnswer;
import ru.nsu.ccfit.terekhov.chat.common.response.response.UserListAnswer;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserListTest extends BaseTest {

    @Test
    public void singleClientUserList() throws IOException {
        chatClient.send(new LoginCommand("user1", "client1"));
        String session = ((SessionSuccessAnswer) chatClient.get()).getSession();
        chatClient.send(new ListCommand(session) );

        UserListAnswer userList = (UserListAnswer) chatClient.get();
        assertThat(userList.getUsers().size(), is(1));
        assertThat(userList.getUsers().get(0).getUserName(), is("user1"));
    }
}
