package ru.nsu.ccfit.terekhov.chat.intergationtest.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.nsu.ccfit.terekhov.chat.server.Server;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.LoginCommand;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;
import ru.nsu.ccfit.terekhov.chat.common.response.response.ErrorAnswer;
import ru.nsu.ccfit.terekhov.chat.common.response.response.SessionSuccessAnswer;
import ru.nsu.ccfit.terekhov.chat.intergationtest.XmlChatClient;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LoginTest extends BaseTest {

    @Test
    public void SimpleLoginTest() throws IOException, InterruptedException {
        chatClient.send(new LoginCommand("user1", "client01"));

        Response response = chatClient.get();
        assertThat( response, instanceOf(SessionSuccessAnswer.class) );
        assertThat( ((SessionSuccessAnswer) response).getSession(), notNullValue() ) ;
    }

    @Test
    public void TwoUserWithSameNames() throws IOException {
        chatClient.send(new LoginCommand("user1", "client01"));
        Response response = chatClient.get();
        assertThat( response, instanceOf(SessionSuccessAnswer.class));

        XmlChatClient otherClient = new XmlChatClient();
        otherClient.send(new LoginCommand("user1", "client01"));
        Response otherResponse = otherClient.get();
        assertThat( otherResponse, instanceOf(ErrorAnswer.class));

        otherClient.close();
    }
}
