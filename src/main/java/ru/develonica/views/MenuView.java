package ru.develonica.views;

/**
 * View that displays menu.
 */
public final class MenuView {

    private static final String menuMessage = """
            [B] - back to parent directory.
            [+] - create NEW directory in CURRENT folder.
            [-] - remove dir/file by ID.
             """;

    public void proceed() {
        System.out.println(menuMessage);
    }
}
