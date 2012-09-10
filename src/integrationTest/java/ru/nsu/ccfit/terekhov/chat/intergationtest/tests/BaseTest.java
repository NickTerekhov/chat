package ru.nsu.ccfit.terekhov.chat.intergationtest.tests;

import org.junit.After;
import org.junit.Before;
import ru.nsu.ccfit.terekhov.chat.intergationtest.XmlChatClient;
import ru.nsu.ccfit.terekhov.chat.server.Server;

import java.io.IOException;

public class BaseTest {
    protected Server server;
    protected Thread serverThread;
    protected XmlChatClient chatClient;

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
}
