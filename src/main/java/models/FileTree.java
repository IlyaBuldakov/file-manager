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

    public FileTree(Collection<Information> info) {
        this.tree = new ArrayList<>();
        this.tree.addAll(info);
    }

    /**
     * Creates empty file tree.
     */
    public FileTree() {
        this.tree = Collections.emptyList();
    }

    /**
     * Returns file tree.
     * @return File tree.
     */
    public List<Information> getTree() {
        return this.tree;
    }

    /**
     * Method that allows display view.
     */
    @Override
    public void displayView() {
        FileTreeView view = new FileTreeView(this);
    }
}
