package main.java.models;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Model of file tree.
 */
public class FileTree {

    List<Information> tree;

    Path treePath;

    String headName;

    public FileTree(Collection<Information> info, Path treePath, String headName) {
        this.tree = new ArrayList<>();
        this.headName = headName;
        this.treePath = treePath;
        this.tree.addAll(info);
    }

    /**
     * Creates empty file tree.
     */
    public FileTree(String headName) {
        this.tree = Collections.emptyList();
        this.headName = headName;
    }

    /**
     * Returns file tree.
     * @return File tree.
     */
    public List<Information> getTree() {
        return this.tree;
    }

    public String getHeadName() {
        return headName;
    }

    public Path getTreePath() {
        return treePath;
    }
}