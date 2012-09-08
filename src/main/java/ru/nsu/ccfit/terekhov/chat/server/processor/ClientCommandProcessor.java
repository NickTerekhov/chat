package ru.nsu.ccfit.terekhov.chat.server.processor;

import ru.nsu.ccfit.terekhov.chat.server.commands.common.Command;
import ru.nsu.ccfit.terekhov.chat.server.processor.handler.common.CommandHandler;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.ClientSocketProcessor;
import ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml.XmlClientSocketProcessor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ClientCommandProcessor implements Runnable {
    private final static int QUEUE_SIZE = 100;
    private final static long DELAY_TIME = 1000;
    private final ArrayBlockingQueue<CommandTask> commandTasksQueue = new ArrayBlockingQueue<CommandTask>(QUEUE_SIZE);
    private final HandlerFactory handlerFactory = new HandlerFactory();

    public void addCommandTask(CommandTask commandTask) throws InterruptedException {
        assert null != commandTask;
        commandTasksQueue.put(commandTask);
        System.out.println("Added " + commandTask.getCommand().getClass().getName() + " Command");
    }


    @Override
    public void run() {
        for (; ; ) {
            try {
                CommandTask task = commandTasksQueue.poll(DELAY_TIME, TimeUnit.MILLISECONDS);
                if (null == task && Thread.currentThread().isInterrupted()) {
                    // todo replace with logger
                    System.out.println("Finishing commandprocessor thread");
                    return;
                }
                if (null != task) {
                    processTask(task);
                }

            } catch (InterruptedException e) {
                // todo replace with logger
                e.printStackTrace();
            }

        }
    }

    private void processTask(CommandTask task) throws InterruptedException {
        assert null != task;
        System.out.println("Process " + task.getCommand().getClass().getName() + " command");
        Command command = task.getCommand();
        CommandHandler commandHandler = handlerFactory.createHandler(command);
        commandHandler.processCommand(command, task.getClientSocketProcessor());
    }


}
