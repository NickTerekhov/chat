import com.sun.xml.internal.ws.util.xml.XmlUtil;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import ru.nsu.ccfit.terekhov.chat.server.commands.xml.factory.CommandNameResolver;
import ru.nsu.ccfit.terekhov.chat.server.commands.xml.impl.XmlUtils;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CommandNameResolverTest {

    @Test
    public void simpleCommandNameTest() throws IOException, SAXException, ParserConfigurationException {
        final String command = "<?xml version=\"1.0\"?>" +
                "<command name=\"login\">" +
                "<name>user</name>" +
                "<type>SimpleClientV01</type>" +
                "</command>";
        Document cmdDocument = XmlUtils.fromString(command);
        String cmdName = new CommandNameResolver().resolveName(cmdDocument);
        assertThat(cmdName, is("login"));

    }


}
