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
        
        String firstWord = words.length > 0 ? words[0].toUpperCase().replaceAll("[^A-Z]", "") : "";
        String lastWord = words.length > 1 ? words[words.length - 1].toUpperCase().replaceAll("[^A-Z]", "") : firstWord;
        
        messageHash = firstTwo + ":" + (messageCounter - 1) + ":" + firstWord + lastWord;
        return messageHash;
    }

    public String SentMessage() {
        return "Choose an option:\n1) Send Message\n2) Disregard Message\n3) Store Message";
    }

    public String printMessages() {
        return "Message ID: " + messageID +
               "\nMessage Hash: " + messageHash +
               "\nRecipient: " + recipient +
               "\nMessage: " + messageText;
    }

    public static int returnTotalMessages() {
        return totalMessages;
    }

    public void increaseTotal() {
        totalMessages++;
    }

    // Simple store method (saves to a text file)
    public void storeMessage() {
        try {
            FileWriter writer = new FileWriter("stored_messages.txt", true);
            writer.write("MessageID: " + messageID + "\n");
            writer.write("MessageHash: " + messageHash + "\n");
            writer.write("Recipient: " + recipient + "\n");
            writer.write("Message: " + messageText + "\n");
            writer.write("------------------------\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving message.");
        }
    }

    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
}