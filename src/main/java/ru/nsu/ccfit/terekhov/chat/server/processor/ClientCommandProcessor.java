package ru.nsu.ccfit.terekhov.chat.server.processor;

import ru.nsu.ccfit.terekhov.chat.server.transfer.common.ClientSocketProcessor;
import ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml.XmlClientSocketProcessor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ClientCommandProcessor implements Runnable
{
	private final static int QUEUE_SIZE = 100;
	private final ArrayBlockingQueue<CommandTask> commandTasksQueue = new ArrayBlockingQueue<CommandTask>(QUEUE_SIZE);

	public void addCommandTask(CommandTask commandTask) throws InterruptedException
	{
		assert null != commandTask;
		commandTasksQueue.put(commandTask);
	}


	@Override
	public void run()
	{
		long delayTime = 1000;


		for (;;)
		{
			try {
				CommandTask task = commandTasksQueue.poll(delayTime, TimeUnit.MICROSECONDS);
				if( null == task && Thread.currentThread().isInterrupted() ) {
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
