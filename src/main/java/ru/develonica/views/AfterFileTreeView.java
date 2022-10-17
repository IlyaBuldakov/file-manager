package ru.develonica.views;

import ru.develonica.services.SizeConverter;

/**
 * View that displays additional information
 * after the output of the file tree.
 */
public class AfterFileTreeView {

    private static final String FILE_TREE_ENDING = "=== File tree %s === \n";

    public static void finishBuild(String headName, float totalSize, int totalCount) {
        System.out.printf(FILE_TREE_ENDING, headName);
        System.out.println("Total size: " + SizeConverter.convert(totalSize));
        System.out.println("Total count: " + totalCount);
        System.out.println("Enter path/number of directory or use hotkeys.");
    }
}