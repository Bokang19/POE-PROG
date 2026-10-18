package com.mycompany.chattapp;

import java.util.Scanner;

public class ChattApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ========== PART 1: LOGIN ==========
        System.out.println("===== QUICKCHAT LOGIN =====");
        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        System.out.print("Enter cell phone number (+27...): ");
        String cell = sc.nextLine();

        Login login = new Login(username, password, cell);

        if (login.checkUserName() && login.checkPasswordComplexity() && login.checkCellPhoneNumber()) {
            System.out.println("Login successful!");
            System.out.println("Welcome to QuickChat.");

            // ========== PART 2: MENU ==========
            while (true) {
                System.out.println("\nPlease choose an option:");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();
                sc.nextLine(); // clear buffer

                if (choice == 1) {
                    // SEND MESSAGES
                    System.out.print("How many messages do you want to send? ");
                    int numMessages = sc.nextInt();
                    sc.nextLine();

                    for (int i = 0; i < numMessages; i++) {
                        System.out.println("\n----- Message " + (i + 1) + " -----");

                        System.out.print("Enter recipient cell number (e.g. +27831234567): ");
                        String recipient = sc.nextLine();

                        System.out.print("Enter your message: ");
                        String messageText = sc.nextLine();

                        if (messageText.length() > 250) {
                            System.out.println("Please enter a message of less than 250 characters.");
                            i--; // try again
                            continue;
                        } else {
                            System.out.println("Message sent");
                        }

                        Message msg = new Message(recipient, messageText);

                        System.out.println(msg.checkRecipientCell());
                        System.out.println("Message Hash: " + msg.createMessageHash());

                        System.out.println(msg.SentMessage());
                        System.out.print("Enter choice (1=Send, 2=Disregard, 3=Store): ");
                        int action = sc.nextInt();
                        sc.nextLine();

                        if (action == 1) {
                            System.out.println("Message successfully sent.");
                            msg.increaseTotal();
                            System.out.println(msg.printMessages());
                        } else if (action == 2) {
                            System.out.println("Press 0 to delete the message.");
                        } else if (action == 3) {
                            msg.storeMessage();
                            System.out.println("Message successfully stored.");
                        }
                    }

                    System.out.println("\nTotal messages sent: " + Message.returnTotalMessages());

                } else if (choice == 2) {
                    System.out.println("Coming Soon.");
                } else if (choice == 3) {
                    System.out.println("Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid option. Please try again.");
                }
            }

        } else {
            System.out.println("Login failed. Please check your details.");
        }

        sc.close();
    }
}