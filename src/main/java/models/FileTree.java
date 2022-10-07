package main.java.models;

import main.java.views.FileTreeView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Model of file tree.
 */
public class FileTree implements Viewable {

    List<Information> tree;

    String headName;

    public FileTree(Collection<Information> info, String headName) {
        this.tree = new ArrayList<>();
        this.headName = headName;
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

    /**
     * Method that allows display view.
     */
    @Override
    public void displayView() {
        FileTreeView view = new FileTreeView(this);
    }
}