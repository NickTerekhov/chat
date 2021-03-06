package ru.nsu.ccfit.terekhov.chat.client.model;

import ru.nsu.ccfit.terekhov.chat.client.model.events.EventHandlerFactory;
import ru.nsu.ccfit.terekhov.chat.client.model.events.EventReceiver;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.*;
import ru.nsu.ccfit.terekhov.chat.common.commands.common.Command;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Answer;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;
import ru.nsu.ccfit.terekhov.chat.common.response.response.ErrorAnswer;
import ru.nsu.ccfit.terekhov.chat.common.response.response.SessionSuccessAnswer;
import ru.nsu.ccfit.terekhov.chat.common.stream.CommandWriter;
import ru.nsu.ccfit.terekhov.chat.common.stream.ResponseReader;

import javax.swing.*;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public abstract class BaseServerManager implements ServerManager {
    protected Socket socket;
    protected ResponseReader responseReader;

    protected ResponseReceiver responseReceiver;
    protected CommandWriter commandWriter;
    protected Thread responseReceiverThread;
    protected UserData userData;
    protected boolean connected = false;
    protected boolean entered = false;
    private EventHandlerFactory handlerFactory;
    private EventReceiver eventReceiver;

    protected abstract ResponseReader createResponseReader(InputStream inputStream);
    protected abstract CommandWriter createCommandWriter(OutputStream outputStream);

    public ResponseReceiver getResponseReceiver() {
        return responseReceiver;
    }

    public void connect(String server, int port) throws IOException {
        if (connected) {
            close();
        }
        socket = new Socket(server, port);
        responseReader = createResponseReader(socket.getInputStream());
        commandWriter = createCommandWriter(socket.getOutputStream());

        assert null != eventReceiver;
        handlerFactory = new EventHandlerFactory(eventReceiver);
        responseReceiver = new ResponseReceiver(responseReader, handlerFactory);
        responseReceiverThread = new Thread(responseReceiver);
        responseReceiverThread.start();
        connected = true;
    }

    public void setEventReceiver(EventReceiver eventReceiver) {
        this.eventReceiver = eventReceiver;
    }


    public void send(Command command) throws IOException {
        if (connected) {
            commandWriter.write(command);
        } else {
            throw new IOException("Cant write into closed manager");
        }
    }

    public Answer getReadedAnswer() {
        try {
            return responseReceiver.getAnswer();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }


    @Override
    public void close() throws IOException {
        if (connected) {
            System.out.println("Shutdown server manager");
            responseReceiverThread.interrupt();

            try {
                responseReceiverThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            closeQuitly(socket);
            connected = false;
        }
    }

    private void closeQuitly(Closeable closeResource) {
        try {
            closeResource.close();
        } catch (IOException e) {

        }
    }

    @Override
    public boolean isEntered() {
        return entered;
    }

    @Override
    public EnterResult tryEnter(String userName) throws IOException {
        LoginCommand loginCommand = new LoginCommand(userName, "CoolGuiClient");
        send(loginCommand);
        Answer answer = getReadedAnswer();
        if (answer instanceof SessionSuccessAnswer) {
            String session = ((SessionSuccessAnswer) answer).getSession();
            userData = new UserData(userName, session);
            entered = true;
            return new EnterResult(answer, true);
        } else {
            return new EnterResult(answer, false);
        }
    }


    @Override
    public void logout() throws IOException {
        try {
            if (isEntered()) {
                LogoutCommand logoutCommand = new LogoutCommand(userData.getSession());
                send(logoutCommand);
            }
        } finally {
           close();
        }
    }

    @Override
    public void requestUserList() throws IOException {
        if (isEntered()) {
            ListCommand listCommand = new ListCommand(userData.getSession());
            send(listCommand);
        }
    }

    @Override
    public void sendMessage(String message) throws IOException {
        if (isEntered()) {

            MessageCommand messageCommand = new MessageCommand();
            messageCommand.setMessage(message);
            messageCommand.setSession(userData.getSession());
            send(messageCommand);
        } else {
            System.err.println("Cant send message when not entered");
        }
    }

    @Override
    public void sendPrivateMessage(String message, String currentUser) throws IOException {
        if (isEntered()) {

            PrivateMessageCommand messageCommand = new PrivateMessageCommand();
            messageCommand.setUser(currentUser);
            messageCommand.setMessage(message);
            messageCommand.setSession(userData.getSession());
            send(messageCommand);
        } else {
            System.err.println("Cant send message when not entered");
        }
    }
}
