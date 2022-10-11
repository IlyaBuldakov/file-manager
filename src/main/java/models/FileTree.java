package main.java.models;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Model of file tree.
 */
public class FileTree {

    List<Information> tree;

    Path treePath;

    String headName;

    private static final String FILE_TREE_ENDING = "=== File tree %s === \n";

    public FileTree(Collection<Information> info, Path treePath, String headName) {
        this.tree = new LinkedList<>();
        this.headName = headName;
        this.treePath = treePath;
        this.tree.addAll(info);
        finishBuild();
    }

    /**
     * Creates empty file tree.
     */
    public FileTree(String headName, Path treePath) {
        this.tree = Collections.emptyList();
        this.treePath = treePath;
        this.headName = headName;
        finishBuild();
    }

    /**
     * Returns file tree.
     * @return File tree.
     */
    public List<Information> getTree() {
        return this.tree;
    }

    public Path getTreePath() {
        return treePath;
    }

    public void finishBuild() {
        System.out.printf(FILE_TREE_ENDING, headName);
        System.out.println("[B] - back to parent directory.");
    }
}