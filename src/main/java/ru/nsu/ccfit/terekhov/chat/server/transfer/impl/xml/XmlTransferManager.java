package ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml;

import org.w3c.dom.Document;
import ru.nsu.ccfit.terekhov.chat.server.commands.xml.impl.XmlUtils;
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
	private final ArrayBlockingQueue<Object> commandTasksQueue = new ArrayBlockingQueue<Object>(QUEUE_SIZE);
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
	public void sendResponse(Answer answer) throws InterruptedException
	{
		assert null != answer;
		commandTasksQueue.put(answer);
	}

	@Override
	public void sendEvent(Event event) throws InterruptedException
	{
		assert null != event;
		commandTasksQueue.put(event);
	}

	@Override
	public void run()
	{

		for(;;) {
			try {
				Object task = commandTasksQueue.poll(DELAY_TIME, TimeUnit.MICROSECONDS);
				if (null == task && Thread.currentThread().isInterrupted()) {
					// todo replace with logger
					System.out.println("Finishing commandprocessor thread");
					return;
				}
				processTask(task);

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

	private void processTask(Object task) throws IOException
	{
		if( task instanceof Answer) {
			processResponse((Answer) task);
		} else if( task instanceof Event ) {
			processEvent((Event) task);
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

	private void processResponse(Answer task) throws IOException
	{
		assert null != task;
		ResponseToXmlSerializer serializer = responseToDocumentCreator.createSerializer(task);
		Document xmlDocument = serializer.ResponseToDocument(task);
		sendDocument(xmlDocument);
	}
}
