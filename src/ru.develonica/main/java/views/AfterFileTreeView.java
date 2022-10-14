package views;

import services.SizeConverter;

public class AfterFileTreeView {

    private static final String FILE_TREE_ENDING = "=== File tree %s === \n";

    public static void finishBuild(String headName, float totalSize) {
        System.out.printf(FILE_TREE_ENDING, headName);
        System.out.println("=== Total size: " + SizeConverter.convert(totalSize) + " ===");
        System.out.println("Enter path/number of directory or use hotkeys.");
        System.out.println("[B] - back to parent directory.");
    }
}