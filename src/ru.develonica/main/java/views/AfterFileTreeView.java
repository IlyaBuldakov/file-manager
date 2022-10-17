package views;

import services.SizeConverter;

public class AfterFileTreeView {

    private static final String FILE_TREE_ENDING = "=== File tree %s === \n";

    public static void finishBuild(String headName, float totalSize, int totalCount) {
        System.out.printf(FILE_TREE_ENDING, headName);
        System.out.println("Total size: " + SizeConverter.convert(totalSize));
        System.out.println("Total count: " + totalCount);
        System.out.println("Enter path/number of directory or use hotkeys.");
        System.out.println();
        System.out.println("[B] - back to parent directory.");
        System.out.println("[+] - create NEW directory in CURRENT folder.");
        System.out.println("[-] - remove dir/file by ID.");
    }
}