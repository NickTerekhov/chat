package ru.nsu.ccfit.terekhov.chat.server.processor;

import ru.nsu.ccfit.terekhov.chat.server.transfer.common.ClientSocketProcessor;
import ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml.XmlClientSocketProcessor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ClientCommandProcessor implements Runnable
{
	private final static int QUEUE_SIZE = 100;
	private final static long DELAY_TIME = 1000;
	private final ArrayBlockingQueue<CommandTask> commandTasksQueue = new ArrayBlockingQueue<CommandTask>(QUEUE_SIZE);

	public void addCommandTask(CommandTask commandTask) throws InterruptedException
	{
		assert null != commandTask;
		commandTasksQueue.put(commandTask);
	}


	@Override
	public void run()
	{
		for (; ; ) {
			try {
				CommandTask task = commandTasksQueue.poll(DELAY_TIME, TimeUnit.MICROSECONDS);
				if (null == task && Thread.currentThread().isInterrupted()) {
					// todo replace with logger
					System.out.println("Finishing commandprocessor thread");
					return;
				}
				processTask(task);

			} catch (InterruptedException e) {
				// todo replace with logger
				e.printStackTrace();
			}

		}
	}

	private void processTask(CommandTask task)
	{

	}


}
