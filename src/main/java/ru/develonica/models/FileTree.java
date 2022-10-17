package ru.develonica.models;

import ru.develonica.views.AfterFileTreeView;
import ru.develonica.views.MenuView;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

/**
 * Model of file tree.
 */
public class FileTree {

    /**
     * File tree content.
     */
    List<Information> tree;

    /**
     * Path file tree.
     */
    Path treePath;

    /**
     * File tree name (directory name).
     */
    String headName;

    /**
     * Total size of all objects in file tree.
     */
    float totalSize;

    /**
     * Total count of all objects.
     */
    int totalCount;

    /**
     * Creates file tree with all data.
     *
     * @param infoList List of {@link Information}.
     * @param treePath Path to file tree.
     * @param headName Name of file tree (directory).
     */
    public FileTree(List<Information> infoList, Path treePath, String headName) {
        this.tree = infoList;
        this.treePath = treePath;
        this.headName = headName;
        this.totalSize = infoList.stream().map(Information::getSize).reduce(0f, Float::sum);
        this.totalCount = infoList.size();
        AfterFileTreeView.finishBuild(headName, totalSize, totalCount);
        MenuView.displayMenu();
    }

    /**
     * Creates empty file tree.
     */
    public FileTree(String headName, Path treePath) {
        this.tree = Collections.emptyList();
        this.treePath = treePath;
        this.headName = headName;
        this.totalSize = 0f;
        AfterFileTreeView.finishBuild(headName, totalSize, 0);
        MenuView.displayMenu();
    }

    public List<Information> getTree() {
        return this.tree;
    }

    public Path getTreePath() {
        return treePath;
    }
}