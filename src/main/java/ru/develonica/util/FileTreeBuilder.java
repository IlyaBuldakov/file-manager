package ru.develonica.util;

import ru.develonica.models.FileTree;
import ru.develonica.models.Information;
import ru.develonica.views.ProgressBarView;

import java.io.File;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

/**
 * Utility file tree builder class.
 */
public final class FileTreeBuilder {

    private static final String HOME_PATH = System.getProperty("user.home");

    private static final ProgressBarView PROGRESS_BAR_VIEW = new ProgressBarView();

    /**
     * Method of creating a file tree.
     *
     * @param destination File tree destination.
     * @return {@link FileTree FileTree}.
     */
    public static FileTree build(String destination) {
        Path pathDest = Path.of(destination);
        File fileDest = pathDest.toFile();
        if (fileDest.exists()) {
            File[] files = fileDest.listFiles();
            if (files != null) {
                List<Information> infoList = createInfoList(files);
                return new FileTree(infoList, pathDest, fileDest.getName());
            }
            return new FileTree(fileDest.getName(), pathDest);
        }
        return build(HOME_PATH);
    }

    /**
     * Method for creating a list of {@link Information information}.
     *
     * @param files File array.
     * @return List of {@link Information information}.
     */
    public static LinkedList<Information> createInfoList(File[] files) {
        int total = files.length * 10;
        LinkedList<Information> infoList = new LinkedList<>();
        int progressCounter = 10;
        for (int i = 0; i < files.length; i++) {
            Information info = new Information(files[i], i + 1);
            infoList.add(info);
            PROGRESS_BAR_VIEW.proceed(progressCounter, total);
            progressCounter += 10;
        }
        return infoList;
    }
}