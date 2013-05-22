package ru.nsu.ccfit.terekhov.chat.client.model;

import ru.nsu.ccfit.terekhov.chat.common.commands.common.Command;
import ru.nsu.ccfit.terekhov.chat.common.stream.CommandWriter;
import ru.nsu.ccfit.terekhov.chat.common.stream.ResponseReader;
import ru.nsu.ccfit.terekhov.chat.common.xml.stream.command.XmlCommandWriter;
import ru.nsu.ccfit.terekhov.chat.common.xml.stream.response.XmlResponseReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class XmlServerManager extends BaseServerManager {


    public XmlServerManager() {

    }


    @Override
    protected ResponseReader createResponseReader(InputStream inputStream) {
       return new XmlResponseReader(inputStream);
    }

    @Override
    protected CommandWriter createCommandWriter(OutputStream outputStream) {
        return new XmlCommandWriter(outputStream);
    }


}
