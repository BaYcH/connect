/*
 * Copyright (c) Mirth Corporation. All rights reserved.
 * http://www.mirthcorp.com
 * 
 * The software in this package is published under the terms of the MPL
 * license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 */

package com.mirth.connect.util;

import com.mirth.commons.encryption.Encryptor;
import com.mirth.connect.donkey.model.message.ConnectorMessage;
import com.mirth.connect.donkey.model.message.Message;
import com.mirth.connect.donkey.model.message.MessageContent;

public class MessageEncryptionUtil {

    public static void decryptMessage(Message message, Encryptor encryptor) {
        for (ConnectorMessage connectorMessage : message.getConnectorMessages().values()) {
            decryptConnectorMessage(connectorMessage, encryptor);
        }
    }

    public static void decryptConnectorMessage(ConnectorMessage connectorMessage, Encryptor encryptor) {
        if (connectorMessage != null) {
            decryptMessageContent(connectorMessage.getRaw(), encryptor);
            decryptMessageContent(connectorMessage.getProcessedRaw(), encryptor);
            decryptMessageContent(connectorMessage.getTransformed(), encryptor);
            decryptMessageContent(connectorMessage.getEncoded(), encryptor);
            decryptMessageContent(connectorMessage.getSent(), encryptor);
            decryptMessageContent(connectorMessage.getResponse(), encryptor);
            decryptMessageContent(connectorMessage.getResponseTransformed(), encryptor);
            decryptMessageContent(connectorMessage.getProcessedResponse(), encryptor);
        }
    }

    public static void decryptMessageContent(MessageContent content, Encryptor encryptor) {
        if (content != null) {
            if (content.getContent() != null && content.isEncrypted()) {
                content.setContent(encryptor.decrypt(content.getContent()));
                content.setEncrypted(false);
            }
        }
    }

    public static void encryptMessage(Message message, Encryptor encryptor) {
        for (ConnectorMessage connectorMessage : message.getConnectorMessages().values()) {
            encryptConnectorMessage(connectorMessage, encryptor);
        }
    }

    public static void encryptConnectorMessage(ConnectorMessage connectorMessage, Encryptor encryptor) {
        if (connectorMessage != null) {
            encryptMessageContent(connectorMessage.getRaw(), encryptor);
            encryptMessageContent(connectorMessage.getProcessedRaw(), encryptor);
            encryptMessageContent(connectorMessage.getTransformed(), encryptor);
            encryptMessageContent(connectorMessage.getEncoded(), encryptor);
            encryptMessageContent(connectorMessage.getSent(), encryptor);
            encryptMessageContent(connectorMessage.getResponse(), encryptor);
            encryptMessageContent(connectorMessage.getResponseTransformed(), encryptor);
            encryptMessageContent(connectorMessage.getProcessedResponse(), encryptor);
        }
    }

    public static void encryptMessageContent(MessageContent content, Encryptor encryptor) {
        if (content != null) {
            if (content.getContent() != null && !content.isEncrypted()) {
                content.setContent(encryptor.encrypt(content.getContent()));
                content.setEncrypted(true);
            }
        }
    }
}