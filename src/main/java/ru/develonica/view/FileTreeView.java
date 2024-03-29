package ru.develonica.view;

import ru.develonica.model.info.FileTree;
import ru.develonica.model.info.Information;
import ru.develonica.model.type.Type;
import ru.develonica.model.service.SizeConverter;

import java.util.List;

/**
 * View that displays {@link FileTree}.
 */
public final class FileTreeView {

    private static final String FILE_TREE_ENDING = "=== File tree %s === \n";

    private final SizeConverter sizeConverter;

    public FileTreeView(SizeConverter sizeConverter) {
        this.sizeConverter = sizeConverter;
    }

    /**
     * Method outputs file tree.
     *
     * @param fileTree {@link FileTree}.
     */
    public void proceed(FileTree fileTree) {
        List<Information> infoList = fileTree.getTree();
        for (Information info : infoList) {
            int number = info.getNumber();
            String objName = info.getObjName();
            Type type = info.getType();
            String size = sizeConverter.convert(info.getSize());
            int count = info.getCount();
            String output = String.format(
                    "%d | Name: %s \\ Type: %s \\ Size: %s \\ Count: %s",
                    number, objName, type, size, count);
            System.out.println("\r" + output);
        }

        System.out.printf(FILE_TREE_ENDING, fileTree.getHeadName());
        String totalBlock = """
                Total size: %s
                Total count: %s
                Enter path/number of directory or use hotkeys.
                """.formatted(
                sizeConverter.convert(fileTree.getTotalSize()),
                fileTree.getTotalCount());
        System.out.println(totalBlock);
    }
}