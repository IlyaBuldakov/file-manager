package views;

/**
 * View that displays menu.
 */
public class MenuView {

    public static void displayMenu() {
        System.out.println("[B] - back to parent directory.");
        System.out.println("[+] - create NEW directory in CURRENT folder.");
        System.out.println("[-] - remove dir/file by ID.");
    }
}
