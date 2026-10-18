package com.mycompany.chattapp;

import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class Message {

    private String messageID;
    private String recipient;
    private String messageText;
    private String messageHash;
    private static int totalMessages = 0;
    private static int messageCounter = 0;

    public Message(String recipient, String messageText) {
        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
        messageCounter++;
    }

    private String generateMessageID() {
        Random rand = new Random();
        long number = 1000000000L + (long) (rand.nextDouble() * 9000000000L);
        return String.valueOf(number);
    }

    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        if (recipient != null && recipient.startsWith("+") && recipient.length() >= 10 && recipient.length() <= 13) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public String createMessageHash() {
        String firstTwo = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        