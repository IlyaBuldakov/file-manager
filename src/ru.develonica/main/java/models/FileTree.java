package models;

import views.AfterFileTreeView;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

/**
 * Model of file tree.
 */
public class FileTree {

    List<Information> tree;

    Path treePath;

    String headName;


    public FileTree(List<Information> infoList, Path treePath, String headName) {
        this.tree = infoList;
        this.headName = headName;
        this.treePath = treePath;
        this.tree.addAll(infoList);
        AfterFileTreeView.finishBuild(headName);
    }

    /**
     * Creates empty file tree.
     */
    public FileTree(String headName, Path treePath) {
        this.tree = Collections.emptyList();
        this.treePath = treePath;
        this.headName = headName;
        AfterFileTreeView.finishBuild(headName);
    }

    /**
     * Returns file tree.
     *
     * @return File tree.
     */
    public List<Information> getTree() {
        return this.tree;
    }

    public Path getTreePath() {
        return treePath;
    }
}