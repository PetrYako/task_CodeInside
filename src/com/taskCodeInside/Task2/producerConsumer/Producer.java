package com.taskCodeInside.Task2.producerConsumer;

import com.taskCodeInside.Task2.model.Message;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private final BlockingQueue<Message> queue;
    private List<Message> messages;

    public Producer(BlockingQueue<Message> queue, List<Message> messages) {
        this.queue = queue;
        this.messages = messages;

    }

    @Override
    public void run() {
        try {
            for (Message m : messages) {
                queue.put(m);
            }
        } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
