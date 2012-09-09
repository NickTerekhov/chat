package ru.nsu.ccfit.terekhov.chat.server.transfer.common;

import java.io.Closeable;

public interface ClientSocketProcessor extends Closeable
{
	UserInfo getUserInfo();
	TransferManager getTransferManager();
	ReceiverManager getReceiverManager();
	ClientManager getClientManager();
}
