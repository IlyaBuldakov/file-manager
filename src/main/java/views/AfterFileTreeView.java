package main.java.views;

public class AfterFileTreeView {

    private static final String FILE_TREE_ENDING = "=== File tree %s === \n";

    public static void finishBuild(String headName) {
        System.out.printf(FILE_TREE_ENDING, headName);
        System.out.println("Enter path/number of directory or use hotkeys.");
        System.out.println("[B] - back to parent directory.");
    }
}