package com.taskCodeInside.Task2.util;

import com.taskCodeInside.Task2.model.Message;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestUtil {

    private TestUtil() {}

    public static LinkedList<Message> getMessages() {
        LinkedList<Message> messages = new LinkedList<>();

        messages.add(new Message("text1"));
        messages.add(new Message("text2"));
        messages.add(new Message("text3"));
        messages.add(new Message("text4"));
        messages.add(new Message("text5"));
        messages.add(new Message("text6"));

        return messages;
    }
}
