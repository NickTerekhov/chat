package ru.nsu.ccfit.terekhov.chat.common.response.xml.transformers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;
import ru.nsu.ccfit.terekhov.chat.common.response.response.UserListAnswer;
import ru.nsu.ccfit.terekhov.chat.common.xml.utils.XmlUtils;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.UserInfo;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class ListAnswerTransformer extends AbstractAnswerTransformer<UserListAnswer> {
    private static final String DOCUMENT_SCHEMA = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">\n" +
            "<xs:element name=\"success\">\n" +
            "    <xs:complexType>\n" +
            "      <xs:sequence>\n" +
            "       <xs:element name=\"listusers\">\n" +
            "           <xs:complexType>\n" +
            "               <xs:sequence>\n" +
            "                   <xs:element name=\"user\" type=\"UserType\" minOccurs=\"0\" maxOccurs=\"unbounded\" />\n" +
            "               </xs:sequence>\n" +
            "           </xs:complexType>\n" +
            "       </xs:element>\n" +
            "       </xs:sequence>\n" +
            "   </xs:complexType>\n" +
            "</xs:element>\n" +
            "<xs:complexType name=\"UserType\">\n" +
            "   <xs:sequence>\n" +
            "       <xs:element name=\"name\" type='xs:string'/>\n" +
            "       <xs:element name=\"type\" type='xs:string'/>\n" +
            "    </xs:sequence>\n" +
            "</xs:complexType>\n" +
            "</xs:schema>";

    public ListAnswerTransformer() {
        super(DOCUMENT_SCHEMA);
    }

    @Override
    protected void buildAnswer(UserListAnswer answer, Element rootElement) {
        Document xmlDocument = rootElement.getOwnerDocument();
        Element userListElement = xmlDocument.createElement("listusers");
        rootElement.appendChild(userListElement);

        for (UserInfo userInfo : answer.getUsers()) {
            Element user = createUserElement(userInfo, xmlDocument);
            userListElement.appendChild(user);
        }
    }

    private Element createUserElement(UserInfo userInfo, Document xmlDocument) {
        Element user = xmlDocument.createElement("user");
        Element userName = xmlDocument.createElement("name");
        user.appendChild(userName);
        userName.appendChild(xmlDocument.createTextNode(userInfo.getUserName()));
        Element clientType = xmlDocument.createElement("type");
        user.appendChild(clientType);
        clientType.appendChild(xmlDocument.createTextNode(userInfo.getClientType()));

        return user;
    }

    @Override
    public Response documentToResponse(Document document) {
        Node listUsereElement = document.getElementsByTagName("listusers").item(0);
        NodeList userNodes = listUsereElement.getChildNodes();
        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
        for (int i = 0; i < userNodes.getLength(); i++) {
            if (userNodes.item(i).getNodeName().equals("user")) {
                Element userElement = (Element) userNodes.item(i);
                UserInfo userInfo = new UserInfo();
                userInfo.setUserName(XmlUtils.getNestedTagValue(userElement, "name"));
                userInfo.setClientType(XmlUtils.getNestedTagValue(userElement, "type"));
                userInfoList.add(userInfo);
            }
        }

        UserListAnswer userListAnswer = new UserListAnswer();
        userListAnswer.setUsers(userInfoList);

        return userListAnswer;
    }

    @Override
    public boolean satitfied(Document xmlDocument) {
        Element rootElement = xmlDocument.getDocumentElement();
        return rootElement.getTagName().equals("success")
                && xmlDocument.getElementsByTagName("listusers").getLength() == 1
                ;
    }
}