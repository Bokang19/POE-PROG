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
