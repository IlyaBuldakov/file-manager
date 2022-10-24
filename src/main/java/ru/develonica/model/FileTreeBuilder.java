package ru.develonica.model;

import ru.develonica.view.ProgressBarView;

import java.io.File;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

/**
 * Utility file tree builder class.
 */
public class FileTreeBuilder {

    private static final String HOME_PATH = System.getProperty("user.home");

    private final ProgressBarView progressBarView;

    private final FileOperationsHandler fileOperationsHandler;

    public FileTreeBuilder(ProgressBarView progressBarView, FileOperationsHandler fileOperationsHandler) {
        this.progressBarView = progressBarView;
        this.fileOperationsHandler = fileOperationsHandler;
    }

    /**
     * Method of creating a file tree.
     *
     * @param destination File tree destination.
     * @return {@link FileTree FileTree}.
     */
    public FileTree build(String destination) {
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
    public LinkedList<Information> createInfoList(File[] files) {
        int total = files.length;
        LinkedList<Information> infoList = new LinkedList<>();
        int progressCounter = 1;
        for (int i = 0; i < files.length; i++) {
            Information info = new Information(files[i], i + 1);
            info.setSize(fileOperationsHandler.calculateSize(files[i]));
            info.setCount(fileOperationsHandler.calculateCount(files[i]));
            infoList.add(info);
            this.progressBarView.proceed(progressCounter, total);
            progressCounter++;
        }
        return infoList;
    }
}