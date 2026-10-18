package com.mycompany.chattapp;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message("+27831234567", "Hi Mike, can you join us for dinner tonight?");
        assertTrue(msg.getMessageText().length() <= 250);
    }

    @Test
    public void testMessageLengthFailure() {
        String longMsg = "A".repeat(260);
        Message msg = new Message("+27831234567", longMsg);
        assertTrue(msg.getMessageText().length() > 250);
    }

    @Test
    public void testRecipientSuccess() {
        Message msg = new Message("+27831234567", "Hello");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientFailureNoPlus() {
        Message msg = new Message("27831234567", "Hello");
        assertTrue(msg.checkRecipientCell().contains("incorrectly formatted") || 
                   msg.checkRecipientCell().contains("international code"));
    }

    @Test
    public void testRecipientFailureTooShort() {
        Message msg = new Message("+2783", "Hello");
        assertTrue(msg.checkRecipientCell().contains("incorrectly formatted") || 
                   msg.checkRecipientCell().contains("international code"));
    }

    @Test
    public void testMessageHashContainsColon() {
        Message msg = new Message("+27118693002", "Hi Mike can you join us tonight");
        assertTrue(msg.createMessageHash().contains(":"));
    }

    @Test
    public void testMessageHashIsUppercase() {
        Message msg = new Message("+27118693002", "Hi Mike can you join us tonight");
        String hash = msg.createMessageHash();
        assertEquals(hash, hash.toUpperCase());
    }

    @Test
    public void testMessageIDLength() {
        Message msg = new Message("+27831234567", "Test");
        assertEquals(10, msg.getMessageID().length());
    }

    @Test
    public void testCheckMessageID() {
        Message msg = new Message("+27831234567", "Test");
        assertTrue(msg.checkMessageID());
    }

    @Test
    public void testSentMessageContainsSend() {
        Message msg = new Message("+27831234567", "Hello");
        assertTrue(msg.SentMessage().contains("Send Message"));
    }

    @Test
    public void testSentMessageContainsDisregard() {
        Message msg = new Message("+27831234567", "Hello");
        assertTrue(msg.SentMessage().contains("Disregard Message"));
    }

    @Test
    public void testSentMessageContainsStore() {
        Message msg = new Message("+27831234567", "Hello");
        assertTrue(msg.SentMessage().contains("Store Message"));
    }

    @Test
    public void testTotalMessagesStartsAtZero() {
        assertEquals(0, Message.returnTotalMessages());
    }

    @Test
    public void testPrintMessagesContainsID() {
        Message msg = new Message("+27831234567", "Hello World");
        assertTrue(msg.printMessages().contains("Message ID:"));
    }
}