package com.taskCodeInside.Task2.service;

import com.taskCodeInside.Task2.model.Message;
import com.taskCodeInside.Task2.producerConsumer.Consumer;
import com.taskCodeInside.Task2.producerConsumer.Producer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

public class EmailService {
    private static Logger log = Logger.getAnonymousLogger();

    public void send(List<Message> messages) {
        BlockingQueue<Message> queue = new ArrayBlockingQueue<>(10);

        Thread producer = new Thread(new Producer(queue, messages));
        Thread consumer = new Thread(new Consumer(queue, this));

        producer.start();
        consumer.start();
    }

    public void postMessage(Message message) {
        log.info("Post message [" + message.getText() + "]");
    }
}
