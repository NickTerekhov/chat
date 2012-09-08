package ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml;

import org.w3c.dom.Document;
import ru.nsu.ccfit.terekhov.chat.server.commands.xml.impl.XmlUtils;
import ru.nsu.ccfit.terekhov.chat.server.response.Response;
import ru.nsu.ccfit.terekhov.chat.server.response.event.common.Event;
import ru.nsu.ccfit.terekhov.chat.server.response.event.xml.EventToXmlSeralizer;
import ru.nsu.ccfit.terekhov.chat.server.response.event.xml.EventToXmlSerializersBuilder;
import ru.nsu.ccfit.terekhov.chat.server.response.answer.Answer;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.TransferManager;

import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class XmlTransferManager implements TransferManager
{
	private final Socket clientSocket;
	private final XmlClientSocketProcessor clientSocketProcessor;

	private final ResponseToDocumentCreator responseToDocumentCreator = new ResponseToDocumentCreator();
	private final EventToXmlSerializersBuilder eventToXmlSerializersBuilder = new EventToXmlSerializersBuilder();
	private final ArrayBlockingQueue<Response> commandTasksQueue = new ArrayBlockingQueue<Response>(QUEUE_SIZE);
	private boolean closed = false;
	private final OutputStream outputStream;
	private final DataOutputStream dataOutputStream;

	private final static int QUEUE_SIZE = 100;
	private final static long DELAY_TIME = 1000;

	
	public XmlTransferManager(Socket clientSocket, XmlClientSocketProcessor clientSocketProcessor) throws IOException
	{
		this.clientSocket = clientSocket;
		this.clientSocketProcessor = clientSocketProcessor;
		this.outputStream = clientSocket.getOutputStream();
		dataOutputStream = new DataOutputStream(outputStream);
	}



	@Override
	public synchronized void close() throws IOException
	{
		if (!closed) {
			outputStream.close();
			closed = true;
		}

	}

	@Override
	public void sendResponse(Response response) throws InterruptedException
	{
		assert null != response;
		commandTasksQueue.put(response);
	}



	@Override
	public void run()
	{

		for(;;) {
			try {
				Response response = commandTasksQueue.poll(DELAY_TIME, TimeUnit.MICROSECONDS);
				if (null == response && Thread.currentThread().isInterrupted()) {
					// todo replace with logger
					System.out.println("Finishing commandprocessor thread");
					return;
				}
				processResponse(response);

			} catch (InterruptedException e) {
				// todo replace with logger
				e.printStackTrace();
			} catch (IOException e) {
				closeQuietly(clientSocketProcessor);
				System.out.println("finished receiver thread");
				return;
			}

		}
	}

	private void closeQuietly(Closeable resourse) {
		try {
			resourse.close();
		} catch (IOException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}

	private void processResponse(Response response) throws IOException
	{
		if( response instanceof Answer) {
			processAnswer((Answer) response);
		} else if( response instanceof Event ) {
			processEvent((Event) response);
		} else {
			// todo good message
			throw new IllegalArgumentException("");
		}
	}

	private void processEvent(Event event) throws IOException
	{
		assert null != event;
		EventToXmlSeralizer serlizer = eventToXmlSerializersBuilder.getSeralizer(event);
		Document eventDocument = serlizer.seralizeEvent(event);
		sendDocument(eventDocument);
	}

	private void sendDocument(Document xmlDocument) throws IOException
	{
		String responseString = XmlUtils.toString(xmlDocument);
		byte[] responseBytes = responseString.getBytes();
		dataOutputStream.writeInt(responseBytes.length);
		dataOutputStream.write(responseBytes);
	}

	private void processAnswer(Answer answer) throws IOException
	{
		assert null != answer;
		ResponseToXmlSerializer serializer = responseToDocumentCreator.createSerializer(answer);
		Document xmlDocument = serializer.ResponseToDocument(answer);
		sendDocument(xmlDocument);
	}
}
