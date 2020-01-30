package com.taskCodeInside.Task2;

import com.taskCodeInside.Task2.model.Message;
import com.taskCodeInside.Task2.service.EmailService;

import java.util.LinkedList;
import java.util.List;

import static com.taskCodeInside.Task2.util.TestUtil.getMessages;

public class Task2 {

    public static void main(String[] args) {
        EmailService emailService = new EmailService();
        emailService.send(getMessages());
    }
}
