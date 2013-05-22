package ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml;

import ru.nsu.ccfit.terekhov.chat.common.xml.stream.command.XmlCommandReader;
import ru.nsu.ccfit.terekhov.chat.common.xml.stream.response.XmlResponseWriter;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.ClientManager;
import ru.nsu.ccfit.terekhov.chat.server.processor.ClientCommandProcessor;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.BaseClientSocketProcessor;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.ReceiverManager;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.TransferManager;

import java.io.IOException;
import java.net.Socket;

public class XmlClientSocketProcessor extends BaseClientSocketProcessor
{

    public XmlClientSocketProcessor(Socket clientSocket, ClientCommandProcessor commandProcessor, ClientManager clientManager) throws IOException {
        super(clientSocket, commandProcessor, clientManager);
        receiverManager = new ReceiverManager( new XmlCommandReader(clientSocket.getInputStream()), this);
        transferManager = new TransferManager(new XmlResponseWriter(clientSocket.getOutputStream()), this);
    }
}
