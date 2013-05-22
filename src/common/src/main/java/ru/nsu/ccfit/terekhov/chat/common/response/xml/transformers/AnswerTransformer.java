package ru.nsu.ccfit.terekhov.chat.common.response.xml.transformers;

import org.w3c.dom.Document;

public interface AnswerTransformer extends ResponseTransformer {
    /**
     * Need to check it's possible to transform {@code xmlDocument}
     * with current transformer
     * @param xmlDocument
     * @return
     */
    boolean satitfied(Document xmlDocument);
}
