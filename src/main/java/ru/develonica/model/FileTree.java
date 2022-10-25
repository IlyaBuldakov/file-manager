package ru.develonica.model;

import ru.develonica.model.service.sizehandler.SizeHandler;
import ru.develonica.model.service.sizehandler.impl.SyncSizeHandler;
import ru.develonica.model.service.sizehandler.impl.ThreadPoolSizeHandler;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

/**
 * Model of file tree.
 */
public class FileTree {

    /*
    Initializing size handler.
    */
    static {
        Optional<ExecutorService> threadPool = ThreadPoolHolder.getInstance();
        if (threadPool.isPresent()) {
            SIZE_HANDLER = new ThreadPoolSizeHandler(threadPool.get());
        } else {
            SIZE_HANDLER = new SyncSizeHandler();
        }
    }

    private static final SizeHandler SIZE_HANDLER;

    private static final String HOME_PATH = System.getProperty("user.home");

    private static final TypeResolver TYPE_RESOLVER = new TypeResolver();

    /**
     * File tree content.
     */
    private final List<Information> tree;

    /**
     * Path file tree.
     */
    private final Path treePath;

    /**
     * File tree name (directory name).
     */
    private final String headName;

    /**
     * Total size of all objects in file tree.
     */
    private final long totalSize;

    /**
     * Method that opens file.
     *
     * @param file File to be opened.
     * @throws IOException Exception.
     */
    public void openFile(File file) throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.open(file);
    }

    /**
     * Method of creating a file tree.
     *
     * @param destination File tree destination.
     * @return {@link FileTree FileTree}.
     */
    public static FileTree build(String destination) throws IOException {
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
    private static LinkedList<Information> createInfoList(File[] files) throws IOException {
        LinkedList<Information> infoList = new LinkedList<>();
        for (int i = 0; i < files.length; i++) {
            Information info = new Information(files[i], i + 1);
            info.setSizeAndCount(
                    calculateSize(files[i]),
                    calculateCount(files[i]));
            info.setType(TYPE_RESOLVER.getType(files[i]));
            infoList.add(info);
        }
        return infoList;
    }

    /**
     * Size calculation method.
     *
     * @param file File.
     * @return Size.
     */
    private static long calculateSize(File file) throws IOException {
        if (file.isDirectory()) {
            return SIZE_HANDLER.calculateDestinationSize(file);
        }
        return Files.size(file.toPath());
    }

    /**
     * Method that calculates count of objects in directory.
     *
     * @param file Destination to be calculated.
     * @return Count of objects in directory.
     */
    private static int calculateCount(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                return files.length;
            } else {
                return 0;
            }
        }
        return 1;
    }

    /**
     * Creates file tree with all data.
     *
     * @param infoList List of {@link Information}.
     * @param treePath Path to file tree.
     * @param headName Name of file tree (directory).
     */
    private FileTree(List<Information> infoList, Path treePath, String headName) {
        this.tree = infoList;
        this.treePath = treePath;
        this.headName = headName;
        this.totalSize = infoList.stream().map(Information::getSize).reduce(0L, Long::sum);
    }

    /**
     * Creates empty file tree.
     */
    private FileTree(String headName, Path treePath) {
        this.tree = Collections.emptyList();
        this.treePath = treePath;
        this.headName = headName;
        this.totalSize = 0L;
    }

    public List<Information> getTree() {
        return this.tree;
    }

    public Path getTreePath() {
        return treePath;
    }

    public String getHeadName() {
        return headName;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public int getTotalCount() {
        return tree.size();
    }
}