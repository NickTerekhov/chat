package ru.nsu.ccfit.terekhov.chat.common.xml.stream;

import org.w3c.dom.Document;
import ru.nsu.ccfit.terekhov.chat.common.xml.utils.XmlUtils;

import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class XmlStreamWriter implements Closeable {
    private final DataOutputStream dataOutputStream;

    public XmlStreamWriter(OutputStream outputStream) {
        this.dataOutputStream = new DataOutputStream(outputStream);
    }

    public void write(Document xmlDocument) throws IOException {
        String responseString = XmlUtils.toString(xmlDocument);
        byte[] responseBytes = responseString.getBytes();
        dataOutputStream.writeInt(responseBytes.length);
        dataOutputStream.write(responseBytes);
    }

    @Override
    public void close() throws IOException {
        dataOutputStream.close();
    }
}
