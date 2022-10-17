package ru.develonica.views;

import java.util.concurrent.CompletableFuture;

public class GreetingView {

    /**
     * The greeting method that is called when the
     * program starts before interacting with the user.
     */
    public static void greetingPage() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println();
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            System.out.println("**** Welcome! Here is your home path *********");
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            System.out.println("***** You can navigate at your file **********");
            System.out.println("***** system using absolute path *************");
            System.out.println("***** or numbers in directory list. **********");
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            System.out.println();
            return null;
        });
    }

    /**
     * Called after each request (line entered).
     */
    public static void pleaseWait() {
        System.out.println("Please wait...");
    }
}