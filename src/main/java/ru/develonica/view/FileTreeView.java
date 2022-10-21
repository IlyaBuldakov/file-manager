package ru.develonica.view;

import ru.develonica.model.FileTree;
import ru.develonica.model.Information;
import ru.develonica.model.Type;
import ru.develonica.service.SizeConverter;

import java.util.List;

public final class FileTreeView {

    private static final String FILE_TREE_ENDING = "=== File tree %s === \n";

    public void proceed(FileTree fileTree) {
        List<Information> infoList = fileTree.getTree();
        for (Information info : infoList) {
            int number = info.getNumber();
            String objName = info.getObjName();
            Type type = info.getType();
            String size = SizeConverter.convert(info.getSize());
            int count = info.getCount();
            String output = String.format(
                    "%d | Name: %s \\ Type: %s \\ Size: %s \\ Count: %s",
                    number, objName, type, size, count);
            System.out.println("\r" + output);
        }

        System.out.printf(FILE_TREE_ENDING, fileTree.getHeadName());
        System.out.println("Total size: " + SizeConverter.convert(fileTree.getTotalSize()));
        System.out.println("Total count: " + fileTree.getTotalCount());
        System.out.println("Enter path/number of directory or use hotkeys.");
    }
}