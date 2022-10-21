package ru.develonica.view;

public final class MenuOperationsView {

    /**
     * Method outputs a message for naming a directory.
     */
    public void enterNameForNewObj() {
        System.out.println("=== Enter name for your NEW directory === ");
    }

    /**
     * Method outputs a message about successfully directory creating.
     */
    public void createSuccess() {
        System.out.println("=== Directory created successfully ===");
    }

    /**
     * Method outputs a message to enter ID of object to delete.
     */
    public void enterIdToDelete() {
        System.out.println("=== Enter ID of object to be deleted ===");
    }

    /**
     * Method outputs a message about successfully deleting directory.
     */
    public void deleteSuccess() {
        System.out.println("=== Deleted successfully ===");
    }
}
