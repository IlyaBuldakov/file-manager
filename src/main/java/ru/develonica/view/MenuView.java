package ru.develonica.view;

/**
 * View that displays menu.
 */
public final class MenuView {

    private static final String MENU_MESSAGE = """
            [B] - back to parent directory.
            [+] - create NEW directory in CURRENT folder.
            [-] - remove dir/file by ID.
             """;

    public void proceed() {
        System.out.println(MENU_MESSAGE);
    }
}
