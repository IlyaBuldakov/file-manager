package ru.develonica.views;

public final class GreetingView {

    private static final String greetingMessage = """
             ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
             **** Welcome! Here is your home path *********
             ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
             ***** You can navigate at your file **********
             ***** system using absolute path *************
             ***** or numbers in directory list. **********
             ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
            """;

    /**
     * The greeting method that is called when the
     * program starts before interacting with the user.
     */
    public void greetingPage() {
        System.out.println(greetingMessage);
    }

    /**
     * Called after each request (line entered).
     */
    public void pleaseWait() {
        System.out.println("Please wait...");
    }
}