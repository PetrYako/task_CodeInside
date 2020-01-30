package com.taskCodeInside.Task2.producerConsumer;

import com.taskCodeInside.Task2.model.Message;
import com.taskCodeInside.Task2.service.EmailService;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue<Message> queue;

    private EmailService service;

    public Consumer(BlockingQueue<Message> queue, EmailService service) {
        this.queue = queue;
        this.service = service;
    }

    @Override
    public void run() {
        try {
            while (queue.size() != 0) {
                Message message = queue.take();
                service.postMessage(message);
            }
        } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
