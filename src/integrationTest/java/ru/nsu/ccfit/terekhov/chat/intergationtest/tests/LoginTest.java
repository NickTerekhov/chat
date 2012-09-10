package ru.nsu.ccfit.terekhov.chat.intergationtest.tests;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.nsu.ccfit.terekhov.chat.Server;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.LoginCommand;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.LogoutCommand;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;
import ru.nsu.ccfit.terekhov.chat.common.response.response.SessionSuccessAnswer;
import ru.nsu.ccfit.terekhov.chat.intergationtest.XmlChatClient;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LoginTest {

    private Server server;
    private Thread serverThread;
    private XmlChatClient chatClient;

    @Before
    public void setUp() throws IOException {
        server = new Server();
        serverThread = new Thread(server);
        serverThread.start();
        chatClient = new XmlChatClient();

    }

    @After
    public void tearDown() throws InterruptedException, IOException {
        chatClient.close();
        server.interrupt();
        serverThread.join();
    }

    @Test
    public void SimpleLoginTest() throws IOException, InterruptedException {


        LoginCommand loginCommand = new LoginCommand();
        loginCommand.setUserName("user1");
        loginCommand.setClientType("client01");

        chatClient.send(loginCommand);

        Response response = chatClient.get();
        assertThat( response, instanceOf(SessionSuccessAnswer.class) );
        assertThat( ((SessionSuccessAnswer) response).getSession(), notNullValue() ) ;
    }

    @Test
    public void TwoUserWithSameNames() {

    }
}
